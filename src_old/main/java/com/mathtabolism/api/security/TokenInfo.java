package com.mathtabolism.api.security;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TokenInfo {
  private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");
  
  private String clientId;
  private Date expires;
  
  public TokenInfo(){
  }
  
  public String getClientId() {
    return clientId;
  }
  
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
  
  public Date getExpires() {
    return expires;
  }
  
  public void setExpires(Date expires) {
    this.expires = expires;
  }
  
  public static TokenInfo createTokenInfo(int secondsToExpireIn, String clientId) {
    TokenInfo token = new TokenInfo();
    
    token.clientId = clientId;
    
    Calendar c = Calendar.getInstance(TIME_ZONE);
    c.add(Calendar.SECOND, secondsToExpireIn);
    
    token.expires = c.getTime();
    
    return token;
  }
  
  public boolean isExpired() {
    Calendar tokenCalendar = Calendar.getInstance(TIME_ZONE);;
    tokenCalendar.setTime(expires);
    
    Calendar c = Calendar.getInstance(TIME_ZONE);
    
    return tokenCalendar.before(c);
  }
}
