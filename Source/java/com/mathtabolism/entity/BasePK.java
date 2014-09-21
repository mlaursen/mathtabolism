/**
 * 
 */
package com.mathtabolism.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * All Primary Keys must have an overridden {@link #equals(Object)} and {@link #hashCode()}. To enforce this, abstract
 * equals and hashCode are added here.
 * 
 * @author mlaursen
 */
@Embeddable
public abstract class BasePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public abstract boolean equals(Object object);
	
	public abstract int hashCode();
}
