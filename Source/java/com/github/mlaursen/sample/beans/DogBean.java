/**
 * 
 */
package com.github.mlaursen.sample.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.github.mlaursen.sample.beans.action.DogActions;
import com.github.mlaursen.sample.bo.DogBO;
import com.github.mlaursen.sample.entity.Dog;

/**
 * @author mlaursen
 * 
 */
@Named
@RequestScoped
public class DogBean extends BaseBean {
  @EJB
  private DogBO dogBO;
  private Dog dog;
  
  public Dog getDog() {
    if(dog == null) {
      dog = new Dog();
    }
    return dog;
  }
  
  public void setDog(Dog dog) {
    this.dog = dog;
  }
  
  public List<Dog> getAllDogs() {
    return dogBO.findAll();
  }
  
  public String updateDogStart() {
    return DogActions.UPDATE_DOG.getAction();
  }
  
  public String updateDogEnd() {
    try {
      dogBO.update(dog);
    }
    catch(EJBException e) {
      sendErrorMessageToUser("Error. Check if the weight is above 0 or call the admin.");
      return DogActions.NONE.getAction();
    }
    
    sendInfoMessageToUser("Operation Complete: Update.");
    return DogActions.LIST_ALL_DOGS.getAction();
  }
  
  public String removeDogStart() {
    return DogActions.REMOVE_DOG.getAction();
  }
  
  public String removeDogEnd(){
    try {
     dogBO.remove(dog);
    } catch (EJBException e) {
     sendErrorMessageToUser("Error. Call the admin");
     return DogActions.NONE.getAction();
    }   

    sendInfoMessageToUser("Operation Complete: Delete");

    return DogActions.LIST_ALL_DOGS.getAction();
   }
  
  public String createDogStart() {
    return DogActions.CREATE_DOG.getAction();
  }
  
  public String createDogEnd(){
    try {
     dogBO.add(dog);
    } catch (EJBException e) {
     sendErrorMessageToUser("Error. Check if the weight is above 0 or call the adm");

     return DogActions.NONE.getAction();
    }  

    sendInfoMessageToUser("Operation Complete: Create");

    return DogActions.LIST_ALL_DOGS.getAction();
   }
  
  public String listAllDogs() {
    return DogActions.LIST_ALL_DOGS.getAction();
  }
}
