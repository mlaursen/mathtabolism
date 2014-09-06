/**
 * 
 */
package com.github.mlaursen.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author mlaursen
 *
 */
@Entity
public class Dog extends BaseEntity {
  
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dog_id")
  @SequenceGenerator(name="seq_dog_id", sequenceName="DOG_ID_SEQ", allocationSize=1)
  private Long id;
  
  private String name;
  private double weight;
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }
  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the weight
   */
  public double getWeight() {
    return weight;
  }
  /**
   * @param weight the weight to set
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }
  
  @Override
  public int hashCode() {
    return id.intValue();
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Dog) {
      Dog d = (Dog) obj;
      return id == d.id;
    }
    return false;
  }
}
