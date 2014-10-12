/**
 * 
 */
package com.mathtabolism.bo.ingredient;

import java.util.List;

import javax.inject.Inject;

import com.mathtabolism.eao.ingredient.BrandEAO;
import com.mathtabolism.entity.ingredient.Brand;

/**
 * 
 * @author mlaursen
 */
public class BrandBO {
	
	@Inject
	private BrandEAO brandEAO;
	/**
	 * 
	 */
	public BrandBO() {
	}
	
	public List<Brand> findAllBrands() {
		return brandEAO.findAllBrands();
	}
}
