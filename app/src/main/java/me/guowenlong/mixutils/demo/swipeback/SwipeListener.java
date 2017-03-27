package me.guowenlong.mixutils.demo.swipeback;

/**
 * des   : 描述
 * author: Administrator
 * email : guowenlong20000@gmail.com
 * time  : 2017年03月17日 下午 3:44.
 */

public interface SwipeListener {

  void onEdgeTouch();

  void onScroll(float f, int i);
}