package me.wenlong.mixutils.util;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * des   : Log日志的工具类 author: Gavin email : guowenlong20000@gmail.com time  : 2016年06月07日 上午 1:03.
 */
public class LogUtils {

  public static String mTAG = "";
  public static boolean allowLog = true;

  private LogUtils() {
  }

  public static void d(String content) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.d(tag, content);
  }

  public static void d(String content, Throwable tr) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.d(tag, content, tr);
  }

  public static void e(String content) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.e(tag, content);
  }

  public static void e(String content, Throwable tr) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.e(tag, content, tr);
  }

  public static void i(String content) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.i(tag, content);
  }

  public static void i(String content, Throwable tr) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.i(tag, content, tr);
  }

  public static void v(String content) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.v(tag, content);
  }

  public static void v(String content, Throwable tr) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.v(tag, content, tr);
  }

  public static void w(String content) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.w(tag, content);
  }

  public static void w(String content, Throwable tr) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.w(tag, content, tr);
  }

  public static void w(Throwable tr) {
    if (!allowLog) {
      return;
    }
    StackTraceElement caller = getStackTrace();
    String tag = generateTag(caller);
    Log.w(tag, tr);
  }

  /**
   * 无论主线程,子线程都toast
   */
  public static void toast(Context context, Object msg) {
    if (!allowLog) {
      return;
    }
    if (isInMainThread()) {
      Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show();
    } else {
      Looper.prepare();
      Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show();
      Looper.loop();
    }
  }

  /**
   * 判断是否为主线程
   */
  public static boolean isInMainThread() {
    return Looper.getMainLooper() == Looper.myLooper();
  }

  /**
   * 获取当前线程的信息
   */
  public static StackTraceElement getStackTrace() {
    return Thread.currentThread().getStackTrace()[4];
  }

  /**
   * 显示日志位置
   */
  private static String generateTag(StackTraceElement caller) {
    if (!TextUtils.isEmpty(mTAG)) {
      return mTAG;
    } else {
      String tag = "%s.%s(L:%d)";
      String callerClazzName = caller.getClassName();
      callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
      tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
      return tag;
    }
  }

  /**
   * 系统日志打印(V)
   */
  public static void libraryV(String content) {
    if (!allowLog) {
      return;
    }
    String tag = "Psyche_Library_Log";
    Log.v(tag, content);
  }

  /**
   * 系统日志打印(E)
   */
  public static void libraryE(String content) {
    if (!allowLog) {
      return;
    }
    String tag = "Psyche_Library_Log";
    Log.e(tag, content);
  }

  /**
   * 定位日志打印(I)
   */
  public static void locationI(String content) {
    if (!allowLog) {
      return;
    }
    String tag = "定位回调";
    Log.e(tag, "百度定位 :" + content);
  }
}