/**
 * 
 */
package com.mathtabolism.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

/**
 * @author mlaursen
 *
 */
public abstract class BaseEntityModelConverter<C, E extends C, M extends C> {
  private Logger logger = Logger.getLogger(BaseEntityModelConverter.class);
  private final String GET = "get";
  private final String SET = "set";
  
  private Class<C> converterClass;
  private Class<E> entityClass;
  private Class<M> modelClass;
  
  protected BaseEntityModelConverter(Class<C> converterClass, Class<E> entityClass, Class<M> modelClass) {
    this.converterClass = converterClass;
    this.entityClass = entityClass;
    this.modelClass = modelClass;
  }
  
  /**
   * Converts an entity into its corresponding model
   * @param entity the entity to convert
   * @return a model
   */
  public M convertEntityToModel(E entity) {
    if(entity != null) {
      try {
        M model = modelClass.newInstance();
        doConversion(entity, model, converterClass);
        return model;
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        logger.error(e);
      }
    }
    
    return null;
  }
  
  /**
   * Converts a model into its corresponding entity
   * @param model the model to convert
   * @return an entity
   */
  public E convertModelToEntity(M model) {
    if(model != null) {
      try {
        E entity = entityClass.newInstance();
        doConversion(model, entity, converterClass);
        return entity;
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        logger.error(e);
      }
    }
    
    return null;
  }
  
  /**
   * Converts a generic <tt>convertFrom</tt> Object to a <tt>convertTo</tt> Object by using the <tt>converterClass</tt>
   * interface.  The convert from and convert to Objects are both subclasses of the converter class interface.
   * 
   * <p>For each setter in the converter class interface, the setter is called through reflection on the <tt>convertTo</tt>
   * Object with the corresponding getter from the <tt>convertFrom</tt> Object.
   * 
   * <p>Expected method call sample:
   * <code><pre>
   * convertTo.setSomething(convertFrom.getSomething());
   * convertTo.setSomethingElse(convertFrom.getSomethingElse());
   * </pre></code>
   * 
   * @param convertFrom an Object to convert from
   * @param convertTo an Object to convert to
   * @param converterClass the interface used for converting
   * @throws InvocationTargetException 
   * @throws IllegalArgumentException 
   * @throws IllegalAccessException 
   */
  private <F extends C, T extends C> void doConversion(F convertFrom, T convertTo, Class<C> converterClass) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Method[] converterMethods = converterClass.getMethods();
    Method[] convertFromMethods = convertFrom.getClass().getMethods();
    Method[] convertToMethods = convertTo.getClass().getMethods();

    String[] getters = getGetters(converterMethods);
    String[] setters = getSetters(converterMethods);
    logger.debug("Getters for contract: " + getters);
    logger.debug("Setters for contract: " + setters);
    
    for(String setter : setters) {
      String getter = setter.replace(SET, GET);
      Method toSet = findSetter(convertToMethods, setter);
      Method toGet = findGetter(convertFromMethods, getter);
      if(toSet == null) {
        logger.error("Missing the setter method '" + setter + "' for the corresponding getter '" + getter + "' from " + convertTo);
      }
      
      if(toGet == null) {
        logger.error("Missing the getter method '" + getter + "' for the corresponding setter '" + setter + "' from " + convertFrom);
      }
      
      if(toSet != null && toGet != null) {
        toSet.invoke(convertTo, toGet.invoke(convertFrom));
      }
    }
  }
  
  
  
  private Method findGetter(Method[] methods, String getterName) {
    for(Method m : methods) {
      if(m.getName().equals(getterName) && isGetter(m)) {
        return m;
      }
    }
    return null;
  }
  
  private Method findSetter(Method[] methods, String setterName) {
    for(Method m : methods) {
      if(m.getName().equals(setterName) && isSetter(m)) {
        return m;
      }
    }
    return null;
  }
  
  private String[] getSetters(Method[] methods) {
    List<String> setters = new ArrayList<>();
    for(Method m : methods) {
      if(isSetter(m)) {
        setters.add(m.getName());
      }
    }
    return setters.toArray(new String[]{});
  }
  
  private String[] getGetters(Method[] methods) {
    List<String> getters = new ArrayList<>();
    for(Method m : methods) {
      if(isGetter(m)) {
        getters.add(m.getName());
      }
    }
    return getters.toArray(new String[]{});
  }
  
  private boolean isGetter(Method m) {
    return m.getName().startsWith(GET) && !Void.TYPE.equals(m.getReturnType()) && m.getParameterTypes().length == 0;
  }
  
  private boolean isSetter(Method m) {
    return m.getName().startsWith(SET) && m.getParameterTypes().length == 1 && Void.TYPE.equals(m.getReturnType());
  }
}
