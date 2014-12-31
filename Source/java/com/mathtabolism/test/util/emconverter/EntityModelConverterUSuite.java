/**
 * 
 */
package com.mathtabolism.test.util.emconverter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author mlaursen
 */
@RunWith(Suite.class)
@SuiteClasses({
  ConvertEntitiesToModelUTest.class, ConvertEntityToModelUTest.class,
  ExtractEntitiesFromModelUTest.class, ConvertEntitiesToModelsUTest.class,
  ExtractEntitiesFromModelsUTest.class
})
public class EntityModelConverterUSuite { }
