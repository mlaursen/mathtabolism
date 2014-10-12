/**
 * 
 */
package com.mathtabolism.entity.ingredient;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.entity.BaseEntity;
import com.mathtabolism.entity.BasePK;
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
public class Ingredient extends BaseEntity {
	
	@Id
	@GeneratedValue(generator = "ingredient_id_gen")
	@GenericGenerator(name="ingredient_id_gen", strategy="com.mathtabolism.util.hibernate.PrimaryKeyGenerator")
	private String id;
	private String name;
	private String brand;
	@Enumerated(EnumType.STRING)
	private IngredientCategory category;
	
	@AttributeOverrides({
		@AttributeOverride(name="unitMeasurement", column=@Column(name="default_serving")),
		@AttributeOverride(name="value", column=@Column(name="default_size"))
	})
	private Measurement serving;
	
	@AttributeOverrides({
		@AttributeOverride(name="unitMeasurement", column=@Column(name="alternate_serving")),
		@AttributeOverride(name="value", column=@Column(name="alternate_size"))
	})
	private Measurement alternateServing;
	
	@AttributeOverride(name="value", column = @Column)
	private Calorie calories;
	@AttributeOverride(name="value", column = @Column)
	private Fat fat;
	@AttributeOverride(name="value", column = @Column)
	private Carbohydrate carbohydrates;
	@AttributeOverride(name="value", column = @Column)
	private Protein protein;
	
	public Ingredient() {
	}
	
	public static class PK extends BasePK {
		private static final long serialVersionUID = -6682394873697416961L;
		
		private String name;
		private String brand;
		public PK() {}
		public PK(String name, String brand) {
			this.name = name;
			this.brand = brand;
		}

		/**
		 * @param object
		 * @return
		 */
		@Override
		public boolean equals(Object object) {
			if(object instanceof PK) {
				PK pk = (PK) object;
				return name.equals(pk.name)
						&& brand.equals(pk.brand);
			}
			return false;
		}

		/**
		 * @return
		 */
		@Override
		public int hashCode() {
			return name.hashCode() + brand.hashCode();
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
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
		if(object instanceof Ingredient) {
			Ingredient i = (Ingredient) object;
			return name.equals(i.name)
					&& brand.equals(i.brand)
					&& category.equals(i.category)
					&& serving.equals(i.serving)
					&& alternateServing.equals(i.alternateServing)
					&& calories.equals(i.calories)
					&& fat.equals(i.fat)
					&& carbohydrates.equals(i.carbohydrates)
					&& protein.equals(i.protein);
		}
		return false;
	}
}
