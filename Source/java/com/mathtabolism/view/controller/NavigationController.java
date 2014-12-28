/**
 * 
 */
package com.mathtabolism.view.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.mathtabolism.view.navigation.Navigatable;

/**
 * <p>
 * This is a class for handling Navigation in <tt>Mathtabolism</tt>.
 * <p>
 * It is a bit unnecessary for a project this small. It was more of a learning experience and to prove it can be done.
 * 
 * <p>
 * This is a managed bean that is request scoped. It can be used from any page to navigate within the project. It could
 * also be used to navigate to external sites if needed. The only use I see for this class is handling navigation in
 * <tt>*.xhtml</tt> files.
 * 
 * <p>
 * Example:
 * 
 * <pre>
 * <code>
 * &lt;p:commandButton value="Navigate to /pages/protected/accountSettings.xhtml"
 *                  action="#{navigationBean.redirect('AccountNav.ACCOUNT_SETTINGS')}" /&gt;
 *                  
 * &lt;h:commandLink value="Navigate to /pages/ingredients/viewIngredients.xhtml"
 *                action="#{navigationBean.redirect('IngredientNav.VIEW_INGREDIENTS')}" /&gt;
 * </code>
 * </pre>
 * 
 * <p>This is a sole-purpose class. It is only meant to handle navigation changes. Technically all Controllers will be able to navigate/redirect.
 * 
 * 
 * @author mlaursen
 */
@Named
@RequestScoped
public class NavigationController extends BaseController {
  private static final long serialVersionUID = 1L;
  private static final String NAVIGATION_PACKAGE = "com.mathtabolism.view.navigation.";
  private static Logger logger = Logger.getLogger(NavigationController.class);
  
  public NavigationController() {
  }
  

  
  /**
   * Converts a String into a {@link Navigatable} Enum from the {@link #NAVIGATION_PACKAGE}.
   * <p>
   * Example:
   * 
   * <pre>
   * {@code
   * redirect('AccountNav.ACCOUNT_SETTINGS')
   * }
   * </pre>
   * 
   * This would redirect to the <tt>/pages/protected/accountSettings.xhtml</tt>
   * 
   * @param page
   *          a Enum.PAGE to lookup
   * @return the new page to redirect to or null if an invalid navigation
   */
  @SuppressWarnings("unchecked")
  public <T extends Enum<T> & Navigatable> String redirect(String page) {
    String[] values = page.split("\\.");
    Class<T> enumClass;
    try {
      enumClass = (Class<T>) Class.forName(NAVIGATION_PACKAGE + values[0]);
      return redirect(Enum.valueOf(enumClass, values[1]));
    }
    catch (ClassNotFoundException | IllegalArgumentException e) {
      logger.error("Unable to find a Navigatable resource from: " + values[0]);
      return null;
    }
  }
}
