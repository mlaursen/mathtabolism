/**
 * 
 */
package com.mathtabolism.entity.ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

import com.mathtabolism.entity.BaseEntity;

/**
 * 
 * @author mlaursen
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Brand.Q_findAllBrands, query="SELECT b FROM Brand b ORDER BY name")
})
public class Brand extends BaseEntity {
	public static final String Q_findAllBrands = "Brand.findAllBrands";
	
	public Brand() {
	}
	
	@Id
	@GeneratedValue(generator = "brand_id_gen")
	@GenericGenerator(name="brand_id_gen", strategy="com.mathtabolism.util.hibernate.PrimaryKeyGenerator")
	private String id;
	private String name;

	/**
	 * 
	 * @return 
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * @param id 
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return 
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Brand) {
			Brand b = (Brand) object;
			return id.equals(b.id);
		}
		return false;
	}
	
	/**
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
