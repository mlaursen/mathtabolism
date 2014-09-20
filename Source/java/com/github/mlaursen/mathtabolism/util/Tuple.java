package com.github.mlaursen.mathtabolism.util;

/**
 * 
 * @author mlaursen
 */
public class Tuple<X, Y> {
	
	public final X x;
	public final Y y;
	
	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Tuple) {
			Tuple<?, ?> tuple = (Tuple<?, ?>) object;
			return x.equals(tuple.x) && y.equals(tuple.y);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("Tuple<%s, %s>", x, y);
	}
	
}
