/**
 * 
 */
package com.mathtabolism.util.emconverter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for automatically converted Entities to Models or Models to Entities.
 * <p>The {@link #converter()} is an Interface class used to help with the conversion.
 * The Entity and the Model must both implement the same interface so that the conversion
 * can be successful.
 * 
 * <p>The {@link #convertTo()} is the corresponding Model or Entity that should be converted to.
 * 
 * <p>For example in the {@linkplain com.mathtabolism.entity.account.Account Account Entity},
 * the <tt>converter</tt> is the {@linkplain com.mathtabolism.dto.Account Account interface}
 * and it gets converted to {@linkplain com.mathtabolism.view.model.account.AccountModel Account Model}
 * 
 * @author mlaursen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EMConverter {

  /**
   * The interface that is used for converting this class to its corresponding class.
   * @return the interface class
   */
  Class<? extends Convertable> converter();
  
  /**
   * The class that should be converted to.
   * @return the class to convert to
   */
  Class<? extends Convertable> convertTo();
}
