package me.wenlong.mixutils.mvp.response;

import java.io.Serializable;

/**
 * des   : BaseEntity author: Gavin email : guowenlong20000@gmail.com time  : 2016年08月29日 下午 4:44.
 */
public interface BaseEntity {

  class BaseBean implements Serializable {

    public long id;
    public String objectId;
  }
}