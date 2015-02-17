package com.mathtabolism.api.security;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class ClientInfo {
  
  private String clientId;
  private String clientSecret;
  private GrantType grantType;
  
  public ClientInfo() {
  }
  
  public static ClientInfo createClientInfo(String clientId, String clientSecret, GrantType grantType) {
    ClientInfo client = new ClientInfo();
    
    client.clientId = clientId;
    client.clientSecret = clientSecret;
    client.grantType = grantType;
    
    return client;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public GrantType getGrantType() {
    return grantType;
  }

  public void setGrantType(GrantType grantType) {
    this.grantType = grantType;
  }
  
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("clientId", clientId).append("clientSecret", clientSecret)
        .append("grantType", grantType).toString();
  }
}
