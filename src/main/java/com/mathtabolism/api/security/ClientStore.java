package com.mathtabolism.api.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ClientStore implements Serializable {
  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(ClientStore.class);
  private static final String CLIENTS_FILE = "clients.properties";
  private static final String DELIMITER = ",";
  private static final int CLIENT_SECRET = 0;
  private static final int GRANT_TYPE = CLIENT_SECRET + 1;
  
  private Map<String, ClientInfo> clients = new ConcurrentHashMap<>(16, 0.9f, 1);
  
  @PostConstruct
  public void init() {
    Properties configProperties = loadProperties();
    
    if(!configProperties.isEmpty()) {
      for(Map.Entry<Object, Object> entry : configProperties.entrySet()) {
        String clientId = (String) entry.getKey();
        String[] clientDetails = ((String) entry.getValue()).split(DELIMITER);
        
        if(clientDetails == null) {
          return;
        }
        
        if(clientDetails.length != 2) {
          logger.error(String.format("Missing client information. Please check the %s file"));
          return;
        }
        
        String clientSecret = clientDetails[CLIENT_SECRET];
        GrantType grantType = GrantType.valueOf(clientDetails[GRANT_TYPE].toUpperCase());
        if(grantType == null) {
          logger.error(String.format("Invalid grant type %s", clientDetails[GRANT_TYPE]));
          return;
        }
        
        add(clientId, ClientInfo.createClientInfo(clientId, clientSecret, grantType));
      }
    }
    
    if(clients.isEmpty()) {
      logger.error("API will be unusable because there are no registered clients.");
    }
  }
  
  protected void add(String key, ClientInfo client) {
    clients.put(key, client);
  }
  
  public ClientInfo get(String key) {
    return clients.get(key);
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
