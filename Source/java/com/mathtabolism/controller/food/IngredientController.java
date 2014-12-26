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
import com.mathtabolism.entity.food.BrandEntity;
import com.mathtabolism.entity.food.IngredientEntity;

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
  
  private List<IngredientEntity> unfilteredIngredients;
  private List<IngredientEntity> filteredIngredients = new ArrayList<>();
  private List<BrandEntity> unfilteredBrands;
  private List<BrandEntity> filteredBrands = new ArrayList<>();
  private List<IngredientCategory> selectedCategories = new ArrayList<>();
  
  public IngredientController() {
  }
  
  /**
   * 
   * @return
   */
  public List<IngredientEntity> getUnfilteredIngredients() {
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
  public List<IngredientEntity> getFilteredIngredients() {
    return filteredIngredients;
  }
  
  public List<BrandEntity> getUnfilteredBrands() {
    if(unfilteredBrands == null) {
      unfilteredBrands = brandBO.findAllBrands();
      Collections.copy(filteredBrands, unfilteredBrands);
    }
    return unfilteredBrands;
  }
  
  public List<BrandEntity> getFilteredBrands() {
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
