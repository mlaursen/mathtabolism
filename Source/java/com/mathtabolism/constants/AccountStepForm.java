/**
 * 
 */
package com.mathtabolism.constants;

/**
 *
 * @author mlaursen
 */
public enum AccountStepForm {
  STEP1,
  STEP2,
  STEP3,
  STEP4,
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
