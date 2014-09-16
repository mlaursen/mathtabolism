/**
 * 
 */
package com.github.mlaursen.mathtabolism.util;

/**
 * 
 * @author laursenm
 */
public class Tuple<T> {
	public final T first;
	public final T second;
	public Tuple(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object != null && object instanceof Tuple) {
			Tuple<?> t = (Tuple<?>) object;
			return first.getClass() == t.first.getClass() && first.equals(t.first) && second.equals(t.second);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("Tuple<%s, %s>", first.toString(), second.toString());
	}
}
