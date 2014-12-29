package com.mathtabolism.util.calculation;

/**
 * An interface used to add common addable objects together.
 * 
 * @author mlaursen
 */
public interface Addable {
  
  /**
   * Attempts to add two Addable objects together
   * @param addable an Addable object
   */
  void add(Addable addable);
}
