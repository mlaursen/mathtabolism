package com.mathtabolism.constants;

/**
 * 
 * @author mlaursen
 *
 */
public enum AccountRole {
  USER, ADMIN;
  
  /**
   * Simple helper to check if a Role is an admin
   * 
   * @param role a role to compare to
   * @return true if the role is the Admin Role
   */
  public static boolean isAdmin(AccountRole role) {
    return ADMIN.equals(role);
  }
}
