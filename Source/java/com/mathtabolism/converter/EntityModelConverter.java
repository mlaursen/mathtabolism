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
public abstract class EntityModelConverter<C, E extends C, M extends C> {
  private Logger logger = Logger.getLogger(EntityModelConverter.class);
  private final String GET = "get";
  private final String SET = "set";
  
  private Class<C> converterClass;
  private Class<E> entityClass;
  private Class<M> modelClass;
  
  protected EntityModelConverter(Class<C> converterClass, Class<E> entityClass, Class<M> modelClass) {
    this.converterClass = converterClass;
    this.entityClass = entityClass;
    this.modelClass = modelClass;
  }
  
  public M convertEntityToModel(E entity) {
    try {
      M model = modelClass.newInstance();
      doConversion(entity, model, converterClass);
      return model;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public E convertModelToEntity(M model) {
    try {
      E entity = entityClass.newInstance();
      doConversion(model, entity, converterClass);
      return entity;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
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
    Method[] converterMethods = converterClass.getDeclaredMethods();
    Method[] convertFromMethods = convertFrom.getClass().getDeclaredMethods();
    Method[] convertToMethods = convertTo.getClass().getDeclaredMethods();

    String[] getters = getGetters(converterMethods);
    String[] setters = getSetters(converterMethods);
    if(getters.length != setters.length) {
      logger.error(String.format("There is an uneven amount of getters (%d) and setters (%d).", getters.length, setters.length));
      return;
    }
    
    for(String setter : setters) {
      String getter = setter.replace(SET, GET); // assumes that it exists.... :/
      Method toSet = findSetter(convertToMethods, setter);
      Method toGet = findGetter(convertFromMethods, getter);
      
      toSet.invoke(convertTo, toGet.invoke(convertFrom));
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
