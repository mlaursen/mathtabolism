/**
 * 
 */
package com.mathtabolism.controller.food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import com.mathtabolism.bo.food.BrandBO;
import com.mathtabolism.bo.food.IngredientBO;
import com.mathtabolism.constants.IngredientCategory;
import com.mathtabolism.controller.BaseController;
import com.mathtabolism.entity.food.Brand;
import com.mathtabolism.entity.food.Ingredient;

/**
 * 
 * @author mlaursen
 */
@Named
@ManagedBean
public class IngredientController extends BaseController {
  
  private static final long serialVersionUID = 3469181205289527376L;
  
  @Inject
  public IngredientBO ingredientBO;
  @Inject
  public BrandBO brandBO;
  
  private List<Ingredient> unfilteredIngredients;
  private List<Ingredient> filteredIngredients = new ArrayList<>();
  private List<Brand> unfilteredBrands;
  private List<Brand> filteredBrands = new ArrayList<>();
  private List<IngredientCategory> selectedCategories = new ArrayList<>();
  
  public IngredientController() {
  }
  
  /**
   * 
   * @return
   */
  public List<Ingredient> getUnfilteredIngredients() {
    if(unfilteredIngredients == null) {
      unfilteredIngredients = ingredientBO.findPopularIngredients();
      Collections.copy(filteredIngredients, unfilteredIngredients);
    }
    return unfilteredIngredients;
  }
  
  /**
   * 
   * @return
   */
  public List<Ingredient> getFilteredIngredients() {
    return filteredIngredients;
  }
  
  public List<Brand> getUnfilteredBrands() {
    if(unfilteredBrands == null) {
      unfilteredBrands = brandBO.findAllBrands();
      Collections.copy(filteredBrands, unfilteredBrands);
    }
    return unfilteredBrands;
  }
  
  public List<Brand> getFilteredBrands() {
    return filteredBrands;
  }
  
  /**
   * 
   * @return
   */
  public IngredientCategory[] getCategories() {
    return IngredientCategory.values();
  }
  
  public List<IngredientCategory> getSelectedCategories() {
    return selectedCategories;
  }
  
}
