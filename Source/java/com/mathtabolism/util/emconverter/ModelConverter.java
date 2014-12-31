/**
 * 
 */
package com.mathtabolism.util.emconverter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mathtabolism.entity.BaseEntity;

/**
 *
 * @author mlaursen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ModelConverter {
  
  Class<? extends BaseEntity>[] entities();
}
