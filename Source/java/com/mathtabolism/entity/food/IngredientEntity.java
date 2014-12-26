/**
 * 
 */
package com.mathtabolism.entity.food;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.entity.BaseGeneratedEntity;
import com.mathtabolism.util.nutrition.Calorie;
import com.mathtabolism.util.nutrition.Carbohydrate;
import com.mathtabolism.util.nutrition.Fat;
import com.mathtabolism.util.nutrition.Protein;
import com.mathtabolism.util.unit.Measurement;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
    @NamedQuery(name = IngredientEntity.Q_findAllIngredients, query = "SELECT i FROM IngredientEntity i ORDER BY i.name, i.brandEntity, i.category DESC"),
    @NamedQuery(name = IngredientEntity.Q_findIngredientsByBrand, query = "SELECT i FROM IngredientEntity i WHERE i.brandEntity = :brand ORDER BY i.name, i.brandEntity, i.category DESC"),
    @NamedQuery(name = IngredientEntity.Q_findIngredientsByCategory, query = "SELECT i FROM IngredientEntity i WHERE i.category = :category ORDER BY i.name, i.brandEntity, i.category DESC"),
    @NamedQuery(name = IngredientEntity.Q_findIngredientsByName, query = "SELECT i FROM IngredientEntity i WHERE UPPER(i.name) LIKE :name ORDER BY i.name, i.brandEntity, i.category DESC")
})
public class IngredientEntity extends BaseGeneratedEntity {
  public static final String Q_findAllIngredients = "IngredientEntity.findAllIngredients";
  public static final String Q_findIngredientsByBrand = "IngredientEntity.findIngredientsByBrand";
  public static final String Q_findIngredientsByCategory = "IngredientEntity.findIngredientsByCategory";
  public static final String Q_findIngredientsByName = "IngredientEntity.findIngredientsByName";
  private String name;
  
  @OneToOne
  @JoinColumn(name = "brand_id")
  private BrandEntity brandEntity;
  @Enumerated(EnumType.STRING)
  private IngredientCategory category;
  
  @AttributeOverrides({
      @AttributeOverride(name = "unitMeasurement", column = @Column(name = "default_serving")),
      @AttributeOverride(name = "value", column = @Column(name = "default_size"))
  })
  private Measurement serving;
  
  @AttributeOverrides({
      @AttributeOverride(name = "unitMeasurement", column = @Column(name = "alternate_serving")),
      @AttributeOverride(name = "value", column = @Column(name = "alternate_size"))
  })
  private Measurement alternateServing;
  
  @AttributeOverride(name = "value", column = @Column)
  private Calorie calories;
  @AttributeOverride(name = "value", column = @Column)
  private Fat fat;
  @AttributeOverride(name = "value", column = @Column)
  private Carbohydrate carbohydrates;
  @AttributeOverride(name = "value", column = @Column)
  private Protein protein;
  
  public IngredientEntity() {
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public BrandEntity getBrand() {
    return brandEntity;
  }
  
  public void setBrand(BrandEntity brandEntity) {
    this.brandEntity = brandEntity;
  }
  
  /**
   * 
   * @return
   */
  public IngredientCategory getCategory() {
    return category;
  }
  
  /**
   * 
   * @param category
   */
  public void setCategory(IngredientCategory category) {
    this.category = category;
  }
  
  /**
   * 
   * @return
   */
  public Measurement getServing() {
    return serving;
  }
  
  /**
   * 
   * @param serving
   */
  public void setServing(Measurement serving) {
    this.serving = serving;
  }
  
  /**
   * 
   * @return
   */
  public Measurement getAlternateServing() {
    return alternateServing;
  }
  
  /**
   * 
   * @param alternateServing
   */
  public void setAlternateServing(Measurement alternateServing) {
    this.alternateServing = alternateServing;
  }
  
  /**
   * 
   * @return
   */
  public Calorie getCalories() {
    return calories;
  }
  
  /**
   * 
   * @param calories
   */
  public void setCalories(Calorie calories) {
    this.calories = calories;
  }
  
  public void setCalories(double calories) {
    this.calories = new Calorie(calories);
  }
  
  /**
   * 
   * @return
   */
  public Fat getFat() {
    return fat;
  }
  
  /**
   * 
   * @param fat
   */
  public void setFat(Fat fat) {
    this.fat = fat;
  }
  
  public void setFat(double fat) {
    this.fat = new Fat(fat);
  }
  
  /**
   * 
   * @return
   */
  public Carbohydrate getCarbohydrates() {
    return carbohydrates;
  }
  
  /**
   * 
   * @param carbohydrates
   */
  public void setCarbohydrates(Carbohydrate carbohydrates) {
    this.carbohydrates = carbohydrates;
  }
  
  public void setCarbohydrates(double carbohydrates) {
    this.carbohydrates = new Carbohydrate(carbohydrates);
  }
  
  /**
   * 
   * @return
   */
  public Protein getProtein() {
    return protein;
  }
  
  /**
   * 
   * @param protein
   */
  public void setProtein(Protein protein) {
    this.protein = protein;
  }
  
  public void setProtein(double protein) {
    this.protein = new Protein(protein);
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof IngredientEntity) {
      IngredientEntity i = (IngredientEntity) object;
      return name.equals(i.name) && brandEntity.equals(i.brandEntity) && category.equals(i.category) && serving.equals(i.serving)
          && alternateServing.equals(i.alternateServing) && calories.equals(i.calories) && fat.equals(i.fat)
          && carbohydrates.equals(i.carbohydrates) && protein.equals(i.protein);
    }
    return false;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("brand", brandEntity)
        .append("name", name).append("caloires", calories).append("fat", fat).append("carbohydrates", carbohydrates)
        .append("protein", protein).append("serving", serving).append("alternateServing", alternateServing).toString();
  }
}
