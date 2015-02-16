/**
 * 
 */
package com.mathtabolism.constants;

/**
 *
 * @author mlaursen
 */
public enum AccountStepForm {
  /**
   * Setting Unit System
   */
  STEP1,

  /**
   * Setting gender, height, and starting weight
   */
  STEP2,
  
  /**
   * Setting the Activity Multiplier
   */
  STEP3,
  
  /**
   * Setting the Recalculation day and the TDEE Formula
   */
  STEP4,
  
  /**
   * Setting the birthday or Age
   */
  STEP5;
  
  public static AccountStepForm next(AccountStepForm currentStep) {
    int current = currentStep.ordinal();
    current++;
    if(current > AccountStepForm.values().length - 1) {
      current--;
    }
    
    return AccountStepForm.values()[current];
  }
  
  public static AccountStepForm previous(AccountStepForm currentStep) {
    int current = currentStep.ordinal();
    current--;
    if(current < 0) {
      current = 0;
    }
    return AccountStepForm.values()[current];
  }
  
  public boolean isFirstStep() {
    return this.ordinal() == 0;
  }
  
  public boolean isLastStep() {
    return this.ordinal() == AccountStepForm.values().length - 1;
  }
}
