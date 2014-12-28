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

import com.mathtabolism.entity.BaseEntity;
import com.mathtabolism.view.model.BaseModel;

/**
 * 
 * @author mlaursen
 * @see EMConverter
 */
@Stateless
public class EntityModelConverter {
  private Logger logger = Logger.getLogger(EntityModelConverter.class);
  
  private static final String ERR = "Unable to convert '%s' because %s.";
  private static final String MISSING_EM_CONVERTER = "the EMConverter annotation does not exist for the class";
  private static final String DEBUG = "Attempted to set '%s.%s' with the getter '%s.%s'.";
  private final String GET = "get";
  private final String SET = "set";
  
  public EntityModelConverter() {
  }
  
  /**
   * Converts an entity to a single model by calling each getter and setter in the {@link EMConverter#converter()} Interface.
   * 
   * <p>If successful, the Model should have all the same data that was in the Entity.
   * 
   * @param entity the entity to convert
   * @return a model or null;
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> M convertEntityToModel(E entity) {
    return doConvert(entity);
  }
  
  /**
   * Converts a model to a single entity by calling each getter and setter in the {@link EMConverter#converter()} Interface.
   * 
   * <p>If successful, the Entity should have all the same data that was in the Model.
   * @param model the model to convert
   * @return an Entity or null
   */
  public <M extends BaseModel & Convertable, E extends BaseEntity & Convertable> E convertModelToEntity(M model) {
    return doConvert(model);
  }
  
  /**
   * Converts a list of Entities into a list of Models by calling each getter and setter in the {@link EMConverter#converter()} Interface.
   * 
   * <p>If successful, each Model should have the same data as the corresponding Entity from the list
   * @param entities a List of Entities
   * @return a list of Models
   */
  public <E extends BaseEntity & Convertable, M extends BaseModel & Convertable> List<M> convertEntitiesToModels(List<E> entities) {
    return doConverts(entities);
  }
  
  /**
   * Converts a list of Models into a list of Entities by calling each getter and setter in the {@link EMConverter#converter()} Interface.
   * 
   * <p>If successful, each Entity should have the same data as the corresponding Model from the list
   * @param models a List of Models
   * @return a list of Entities
   */
  public <M extends BaseModel & Convertable, E extends BaseEntity & Convertable> List<E> convertModelsToEntities(List<M> models) {
    return doConverts(models);
  }
  
  /**
   * Converts all items in the <tt>toConvertFromList</tt> to a new List and returns it. This is a helper method for
   * {@link #convertEntitiesToModels(List)} and {@link #convertModelsToEntities(List)} since they are both doing the
   * same logic. The only difference was the order of passing parameters.
   * 
   * <p>Explaining the generics..<br>
   * The generic <b><tt>F</tt></b> means an item to convert <b><tt>F</tt></b>rom while the generic <b><tt>T</tt></b>
   * means an item to convert <b><tt>T</tt></b>o.
   * @param toConvertFromList a List of Convertable items to convert to their counterpart
   * @return a List of Convertable items created from the <tt>toConvertFromList</tt>
   */
  private <F extends Convertable, T extends Convertable> List<T> doConverts(List<F> toConvertFromList) {
    List<T> convertedList = new ArrayList<>();
    for(F toConvertFrom : toConvertFromList) {
      T converted = doConvert(toConvertFrom);
      convertedList.add(converted);
      
      if(converted == null) {
        logger.debug("Null was retuned when attempted to convert " + toConvertFrom + " to it's model counterpart.");
      }
    }
    return convertedList;
  }
  
  /**
   * Converts a Convertable object to another convertable object. A convertable object must have
   * an {@link EMConverter} to be able to get the object to convert to and the Convertable Interface to use
   * for retrieving the getters and setters.
   * 
   * <p>Explaining the generics..<br>
   * The generic <b><tt>F</tt></b> means an item to convert <b><tt>F</tt></b>rom while the generic <b><tt>T</tt></b>
   * means an item to convert <b><tt>T</tt></b>o.
   * @param toConvertFrom
   * @return a converted object or null
   * @see EMConverter
   */
  @SuppressWarnings("unchecked")
  private <F extends Convertable, T extends Convertable> T doConvert(F toConvertFrom) {
    if(toConvertFrom == null) {
      return null;
    }
    
    EMConverter emConverter = toConvertFrom.getClass().getAnnotation(EMConverter.class);
    if(emConverter == null) {
      logger.debug(String.format(ERR, toConvertFrom.getClass(), MISSING_EM_CONVERTER));
      return null;
    }
    
    Class<? extends Convertable> methodsClass = emConverter.converter();
    Method[] availableMethods = methodsClass.getMethods();
    List<Method> getters = getGetters(availableMethods);
    List<Method> setters = getSetters(availableMethods);
    if(getters.size() != setters.size()) {
      logger.error("The getters and setters lengths do not match.");
      return null;
    }
    
    try {
      T converted = (T) emConverter.convertTo().newInstance();
      for(int i = 0; i < getters.size(); i++) {
        Method getter = getters.get(i);
        Method setter = setters.get(i);
        
        logger.debug(String.format(DEBUG, converted.getClass(), setter, toConvertFrom.getClass(), getter));
        try {
          setter.invoke(converted, getter.invoke(toConvertFrom));
        } catch(IllegalAccessException e) {
          logger.debug(String.format(ERR, toConvertFrom.getClass(), " of an illegal access exception"));
          logger.error(e);
        } catch (IllegalArgumentException e) {
          logger.debug(String.format(ERR, toConvertFrom.getClass(), " of illegal arguments"));
          logger.error(e);
        } catch(InvocationTargetException e) {
          logger.debug(String.format(ERR, toConvertFrom.getClass(), " of an invocation target exception"));
          logger.error(e);
        }
      }
      
      return converted;
    } catch (InstantiationException | IllegalAccessException e) {
      logger.debug(String.format(ERR, toConvertFrom.getClass(), " of an instantiation exception"));
      logger.error(e);
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
