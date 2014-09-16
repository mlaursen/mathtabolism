/**
 * 
 */
package com.github.mlaursen.mathtabolism.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author laursenm
 */
@Embeddable
public abstract class BasePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public abstract boolean equals(Object object);
	public abstract int hashCode();
}
