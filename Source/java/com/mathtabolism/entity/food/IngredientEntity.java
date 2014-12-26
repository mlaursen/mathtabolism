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
import com.mathtabolism.emcontract.Ingredient;
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
public class IngredientEntity extends BaseGeneratedEntity implements Ingredient {
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
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public void setName(String name) {
    this.name = name;
  }
  
  public BrandEntity getBrand() {
    return brandEntity;
  }
  
  public void setBrand(BrandEntity brandEntity) {
    this.brandEntity = brandEntity;
  }
  
  @Override
  public IngredientCategory getCategory() {
    return category;
  }
  
  @Override
  public void setCategory(IngredientCategory category) {
    this.category = category;
  }
  
  @Override
  public Measurement getServing() {
    return serving;
  }
  
  @Override
  public void setServing(Measurement serving) {
    this.serving = serving;
  }
  
  @Override
  public Measurement getAlternateServing() {
    return alternateServing;
  }
  
  @Override
  public void setAlternateServing(Measurement alternateServing) {
    this.alternateServing = alternateServing;
  }
  
  @Override
  public Calorie getCalories() {
    return calories;
  }
  
  @Override
  public void setCalories(Calorie calories) {
    this.calories = calories;
  }
  
  @Override
  public Fat getFat() {
    return fat;
  }
  
  @Override
  public void setFat(Fat fat) {
    this.fat = fat;
  }
  
  @Override
  public Carbohydrate getCarbohydrates() {
    return carbohydrates;
  }
  
  @Override
  public void setCarbohydrates(Carbohydrate carbohydrates) {
    this.carbohydrates = carbohydrates;
  }
  
  @Override
  public Protein getProtein() {
    return protein;
  }
  
  @Override
  public void setProtein(Protein protein) {
    this.protein = protein;
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
