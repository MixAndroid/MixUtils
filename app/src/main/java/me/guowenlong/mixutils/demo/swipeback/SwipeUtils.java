package me.guowenlong.mixutils.demo.swipeback;

import java.util.Stack;

/**
 * des   : 描述
 * author: Administrator
 * email : guowenlong20000@gmail.com
 * time  : 2017年03月16日 下午 12:02.
 */

public class SwipeUtils {

  private static Stack<SwipeBackLayout> mSwipeBackLayouts = new Stack<>();

  public static void onCreate(SwipeBackLayout _SwipeBackLayout) {
    mSwipeBackLayouts.add(_SwipeBackLayout);
  }

  public static void onDestory(SwipeBackLayout _SwipeBackLayout) {
    mSwipeBackLayouts.remove(_SwipeBackLayout);
  }

  public static SwipeBackLayout getSwipeBackLayout(SwipeBackLayout _SwipeBackLayout) {
    int i = mSwipeBackLayouts.indexOf(_SwipeBackLayout);
    if (i <= 0) {
      return null;
    }
    return mSwipeBackLayouts.get(i - 1);
  }
}