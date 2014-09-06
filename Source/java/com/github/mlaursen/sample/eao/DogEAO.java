/**
 * 
 */
package com.github.mlaursen.sample.eao;

import javax.ejb.Stateless;

import com.github.mlaursen.sample.entity.Dog;

/**
 * @author mlaursen
 *
 */
@Stateless
public class DogEAO extends BaseEAO<Dog> {

  /**
   * @param entityClass
   */
  public DogEAO() {
    super(Dog.class);
  }
}
