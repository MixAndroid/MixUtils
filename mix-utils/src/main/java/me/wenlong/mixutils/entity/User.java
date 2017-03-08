package me.wenlong.mixutils.entity;

import java.io.Serializable;

/**
 * des   : 用户实体类 author: Gavin email : guowenlong20000@gmail.com time  : 2016年08月23日 下午 2:57.
 */
public class User implements Serializable {

  private String phone;
  private String authToken;
  private String tokenKey = "nothing with tokenKey";

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getTokenKey() {
    return tokenKey;
  }

  public void setTokenKey(String tokenKey) {
    this.tokenKey = tokenKey;
  }
}
