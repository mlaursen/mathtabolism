/**
 * 
 */
package com.github.mlaursen.sample.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;

import com.github.mlaursen.sample.eao.DogEAO;
import com.github.mlaursen.sample.entity.Dog;

/**
 * @author mlaursen
 *
 */
@Stateless
public class DogBO {
  
  @EJB
  private DogEAO dogEAO;
  
  public void add(Dog d) {
    dogEAO.add(d);
  }
  
  public Dog update(Dog d) {
    isValid(d);
    return dogEAO.update(d);
  }
  
  public void remove(Dog d) {
    dogEAO.remove(d);
  }
  
  public Dog find(int id) {
    return dogEAO.findById(id);
  }
  
  public List<Dog> findAll() {
    return dogEAO.findAll();
  }
  
  private void isValid(Dog d) {
    boolean isValid = true;
    if(d != null) {
      isValid = StringUtils.isNotBlank(d.getName()) && d.getWeight() >= 0;
    }
    if(!isValid) {
      throw new IllegalArgumentException("The dog is missing data. Check the name and weight, they should have a value.");
    }
  }
}
