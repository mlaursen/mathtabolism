package com.mathtabolism.api.security;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccessTokenStore implements Serializable {
  private static final long serialVersionUID = 1L;

  private final Map<String, TokenInfo> tokens = new ConcurrentHashMap<>(16, 0.9f, 1);
  
  public void add(String key, TokenInfo tokenInfo)  {
    tokens.put(key, tokenInfo);
  }
  
  public TokenInfo get(String key) {
    return tokens.get(key);
  }
  
  
}
