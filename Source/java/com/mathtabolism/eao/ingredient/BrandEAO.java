/**
 * 
 */
package com.mathtabolism.eao.ingredient;

import java.util.List;

import javax.persistence.TypedQuery;

import com.mathtabolism.eao.BaseEAO;
import com.mathtabolism.entity.ingredient.Brand;

/**
 * 
 * @author mlaursen
 */
public class BrandEAO extends BaseEAO<Brand> {
	public BrandEAO() {
		super(Brand.class);
	}
	
	public List<Brand> findAllBrands() {
		TypedQuery<Brand> q = em.createNamedQuery(Brand.Q_findAllBrands, Brand.class);
		return q.getResultList();
	}
}
