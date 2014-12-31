/**
 * 
 */
package com.mathtabolism.util.emconverter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.mathtabolism.dto.GeneratedIdDto;
import com.mathtabolism.entity.BaseEntity;
import com.mathtabolism.view.model.BaseModel;

/**
 *
 * @author mlaursen
 */
@Stateless
public class EntityModelConverter {
  private static Logger logger = Logger.getLogger(EntityModelConverter.class);
  private static final String GET = "get";
  private static final String SET = "set";
  
  private static final String ERR = "Unable to convert '%s' because %s.";
  private static final String MISSING_MODEL_CONVERTER = "the ModelConverter annotation is missing on the model";
  private static final String MISSING_ENTITY_CONVERTER = "the EntityConverter annotation is missing on the entity";
  private static final String ENTITY_DEFAULTED = "An entity was not given to extract so it was defaulted to %s";
  private static final String NO_ENTITY = "no entity was found to convert";
  
  public EntityModelConverter() {
  }
  
  /**
   * Attempts to extract the first Entity from the {@link ModelConverter#entities()} from the List of Models
   * and create a List of that Entity
   * @param models the List of Models
   * @return a List of Entities or null
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> List<E> extractEntitiesFromModels(List<M> models) {
    return extractEntitiesFromModels(models, null);
  }
  
  /**
   * Attempts to extract the given Entity from the List of Models and create a List of that Entity
   * @param models a List of Models
   * @param entityClass an Entity class
   * @return a List of Entities or null
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> List<E> extractEntitiesFromModels(List<M> models, Class<E> entityClass) {
    if(models == null) {
      return null;
    }
    
    List<E> entities = new ArrayList<>();
    for(M model : models) {
      entities.add(extractEntityFromModel(model, entityClass));
    }
    return entities;
  }
  
  /**
   * Attempts to convert a List of Entities to a List of Models
   * @param entities the List of Entities
   * @return a list of Models or null
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> List<M> convertEntitiesToModels(List<E> entities) {
    if(entities == null) {
      return null;
    }
    
    List<M> models = new ArrayList<>();
    for(E entity : entities) {
      models.add(convertEntityToModel(entity));
    }
    return models;
  }
  
  /**
   * Attempts to extract the first Entity from the {@link ModelConverter#entities()} from a given Model
   * @param model the Model to extract an Entity from
   * @return the extracted Entity or null
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> E extractEntityFromModel(M model) {
    return extractEntityFromModel(model, null);
  }
  
  /**
   * Attempts to extract the given Entity from the given Model.
   * 
   * @param model the Model to extract an Entity from
   * @param entityToExtract the Entity class to extract
   * @return the extracted Entity or null
   */
  @SuppressWarnings("unchecked")
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> E extractEntityFromModel(M model, Class<E> entityToExtract) {
    if(model == null) {
      return null;
    }
    
    ModelConverter modelConverter = model.getClass().getAnnotation(ModelConverter.class);
    if(modelConverter == null) {
      logger.error(String.format(ERR, model.getClass(), MISSING_MODEL_CONVERTER));
      return null;
    }
    
    Class<E>[] availableEntities = (Class<E>[]) modelConverter.entities();
    if(entityToExtract == null) {
      entityToExtract = availableEntities[0];
      logger.debug(String.format(ENTITY_DEFAULTED, entityToExtract));
    } else {
      for(Class<E> eClass : availableEntities) {
        if(eClass.equals(entityToExtract)) {
          entityToExtract = eClass;
          break;
        }
      }
    }
    
    if(entityToExtract == null) {
      logger.error(String.format(ERR, model.getClass(), NO_ENTITY));
      return null;
    }
    
    
    EntityConverter entityConverter = entityToExtract.getAnnotation(EntityConverter.class);
    if(entityConverter == null) {
      logger.error(String.format(ERR, model.getClass(), MISSING_ENTITY_CONVERTER));
      return null;
    }
    
    Class<? extends Convertable> converterDto = entityConverter.converterDto();
    Method[] availableMethods = converterDto.getMethods();
    List<Method> getters = getGetters(availableMethods);
    List<Method> setters = getSetters(availableMethods);
    
    try {
      E entity = entityToExtract.newInstance();
      for(int i = 0; i < getters.size() && i < getters.size(); ++i) {
        Method getter = getters.get(i);
        Method setter = setters.get(i);
        
        setter.invoke(entity, getter.invoke(model));
      }
      
      if(model instanceof GeneratedIdDto && entity instanceof GeneratedIdDto) {
        String id = entity.getClass().getSimpleName() + "Id";
        Method getter = findMethod(model.getClass(), GET + id);
        Method setter = findMethod(entity.getClass(), SET + "Id");
        
        setter.invoke(entity, getter.invoke(model));
      }
      
      return entity;
    } catch (InstantiationException e) {
      logger.debug(String.format(ERR, model.getClass(), " of an instantiation exception"));
      logger.error(e);
    } catch(IllegalAccessException e) {
      logger.debug(String.format(ERR, model.getClass(), " of an illegal access exception"));
      logger.error(e);
    } catch (IllegalArgumentException e) {
      logger.debug(String.format(ERR, model.getClass(), " of illegal arguments"));
      logger.error(e);
    } catch (InvocationTargetException e) {
      logger.debug(String.format(ERR, model.getClass(), " of an invocation target exception"));
      logger.error(e);
    }
    
    return null;
  }
  
  /**
   * Attempts to convert an entity to the {@link EntityConverter#toModel()} class. The model is will be created.
   * @param entity the entity to convert
   * @return the converted model or null
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> M convertEntityToModel(E entity) {
    return convertEntityToModel(entity, null);
  }
  
  /**
   * Attempts to convert an entity to an already existing model. If the given model is null, a new model is generated from
   * the {@link EntityConverter#toModel()} class.
   * @param entity the entity to convert
   * @param model the possibly already existing model to update or create
   * @return the converted model or null
   */
  @SuppressWarnings("unchecked")
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> M convertEntityToModel(E entity, M model) {
    if(entity == null) {
      return model;
    }

    EntityConverter entityConverter = entity.getClass().getAnnotation(EntityConverter.class);
    if(entityConverter == null) {
      logger.error(String.format(ERR, entity.getClass(), MISSING_ENTITY_CONVERTER));
      return model;
    }
    Class<? extends Convertable> converterDto = entityConverter.converterDto();
    Class<M> modelClass = (Class<M>) entityConverter.toModel();
    Method[] availableMethods = converterDto.getMethods();
    List<Method> getters = getGetters(availableMethods);
    List<Method> setters = getSetters(availableMethods);
    
    try {
      if(model == null) {
        model = modelClass.newInstance();
      }
      for(int i = 0; i < getters.size() && i < getters.size(); ++i) {
        Method getter = getters.get(i);
        Method setter = setters.get(i);
        
        setter.invoke(model, getter.invoke(entity));
      }
      
      if(model instanceof GeneratedIdDto && entity instanceof GeneratedIdDto) {
        String id = entity.getClass().getSimpleName() + "Id";
        Method getter = findMethod(entity.getClass(), GET + "Id");
        Method setter = findMethod(model.getClass(), SET + id);
        
        setter.invoke(model, getter.invoke(entity));
      }
      
      return model;
    } catch (InstantiationException e) {
      logger.debug(String.format(ERR, entity.getClass(), " of an instantiation exception"));
      logger.error(e);
    } catch(IllegalAccessException e) {
      logger.debug(String.format(ERR, entity.getClass(), " of an illegal access exception"));
      logger.error(e);
    } catch (IllegalArgumentException e) {
      logger.debug(String.format(ERR, entity.getClass(), " of illegal arguments"));
      logger.error(e);
    } catch (InvocationTargetException e) {
      logger.debug(String.format(ERR, entity.getClass(), " of an invocation target exception"));
      logger.error(e);
    }
    return model;
  }
  
  /**
   * Attempts to fnd a given method name in a given class
   * @param classToSearch the Class to serach methods in
   * @param methodName the method name to find
   * @return the method or null
   */
  private Method findMethod(Class<?> classToSearch, String methodName) {
    Method[] methods = classToSearch.getMethods();
    
    for(Method method : methods) {
      if(method.getName().equals(methodName)) {
        return method;
      }
    }
    return null;
  }
  
  /**
   * Gets all the setter methods from the list of available methods. The setter methods
   * are then sorted by method name to be able to sync with the getter methods.
   * @param availableMethods an array of Methods from the Converter Interface to use
   * @return a List of sorted setter Methods
   */
  private List<Method> getSetters(Method[] availableMethods) {
    return Arrays.asList(availableMethods).stream().filter(m -> isSetter(m)).sorted(new MethodSort()).collect(Collectors.toList());
  }
  
  /**
   * Gets all the getter methods from the list of available methods. The getter methods
   * are then sorted by method name to be able to sync with the setter methods.
   * @param availableMethods an array of Methods from the Converter Interface to use
   * @return a List of sorted getter Methods
   */
  private List<Method> getGetters(Method[] availableMethods) {
    return Arrays.asList(availableMethods).stream().filter(m -> isGetter(m)).sorted(new MethodSort()).collect(Collectors.toList());
  }
  
  /**
   * Checks if a method is considered a getter. A getter method:
   * <ul>
   * <li>starts with "get"
   * <li>does not have a void return type
   * <li>does not have any parameters
   * </ul>
   * @param m the method to check
   * @return true if the method is considered a getter method
   */
  private boolean isGetter(Method m) {
    return m.getName().startsWith(GET) && !Void.TYPE.equals(m.getReturnType()) && m.getParameterTypes().length == 0;
  }
  
  /**
   * Checks if a method is considered a setter. A setter method:
   * <ul>
   * <li>starts with "set"
   * <li>has a void return type
   * <li>has 1 parameter
   * </ul>
   * @param m the method to check
   * @return true if the method is considered a getter method
   */
  private boolean isSetter(Method m) {
    return m.getName().startsWith(SET) && m.getParameterTypes().length == 1 && Void.TYPE.equals(m.getReturnType());
  }
  
  private static class MethodSort implements Comparator<Method> {

    @Override
    public int compare(Method m1, Method m2) {
      return m1.getName().compareTo(m2.getName());
    }
    
  }
}
