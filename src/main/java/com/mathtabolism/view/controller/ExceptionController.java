/**
 * 
 */
package com.mathtabolism.view.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mlaursen
 */
@Named
@RequestScoped
public class ExceptionController extends BaseController {
  private static final long serialVersionUID = 1L;
  private static final String ERROR = "javax.servlet.error.%s";
  private static final String EXCEPTION = "exception";
  private static final String FORWARD = "javax.servlet.forward.%s";
  private static final String STATUS_CODE = "status_code";
  private static final String EXCEPTION_TYPE = "exception_type";
  private static final String REQUEST_URI = "request_uri";
  
  public ExceptionController() {
  }
  
  private Map<String, Object> requestMap;
  @PostConstruct
  public void init() {
    requestMap = getContext().getExternalContext().getRequestMap();
  }
  
  public String[][] getErrorDescriptions() {
    return new String[][] {
        {"Status Code", get(ERROR, STATUS_CODE)},
        {"Exception Type", get(ERROR, EXCEPTION_TYPE)},
        {"Request Uri", get(ERROR, REQUEST_URI)},
        {"Forward Uri", get(FORWARD, REQUEST_URI)},
        {"Stack Trace", getStackTrace()}
    };
  }
  
  private String get(String formatter, String which) {
    Object o = requestMap.get(String.format(formatter, which));
    return o == null ? null : o.toString();
  }
  
  private String getStackTrace() {
    Throwable t = (Throwable) requestMap.get(String.format(ERROR, EXCEPTION));
    StringWriter sw = new StringWriter();
    t.printStackTrace(new PrintWriter(sw, true));
    return sw.toString();
    
  }
}
