package com.mathtabolism.api.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ClientStore implements Serializable {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(ClientStore.class);
  private static final String CLIENTS_FILE = "clients.properties";
  
  private Map<String, ClientInfo> clients = new ConcurrentHashMap<>();
  
  @PostConstruct
  public void init() {
    Properties configProperties = loadProperties();
  }
  
  
  private Properties loadProperties() {
    Properties p = new Properties();
    InputStream is = this.getClass().getClassLoader().getResourceAsStream(CLIENTS_FILE);
    
    if(is != null) {
      try {
        p.load(is);
      } catch(IOException e) {
        logger.error(e);
      }
    } else {
      logger.error(String.format("Could not read %s", CLIENTS_FILE));
    }
    return p;
  }
}
