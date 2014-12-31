/**
 * 
 */
package com.mathtabolism.util.emconverter2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mathtabolism.view.model2.BaseModel;

/**
 *
 * @author mlaursen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EntityConverter {
  
  Class<? extends Convertable> converterDto();
  
  Class<? extends BaseModel> toModel();
}
