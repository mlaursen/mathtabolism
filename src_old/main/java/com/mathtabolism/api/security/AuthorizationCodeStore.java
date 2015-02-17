package com.mathtabolism.api.security;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthorizationCodeStore implements Serializable {
  private static final long serialVersionUID = -3125704300783909099L;
  private final Map<String, TokenInfo> codes = new ConcurrentHashMap<>(16, 0.9f, 1);
  
  public void add(String key, TokenInfo tokenInfo) {
    codes.put(key, tokenInfo);
  }
  
  public TokenInfo get(String key) {
    return codes.get(key);
  }
  
  public void remove(String key) {
    codes.remove(key);
  }
}
