package me.guowenlong.mixutils.demo;

import android.app.Application;
import me.wenlong.mixutils.MixUtils;

/**
 * Created by hz51390 on 2017/3/7.
 */

public class MainApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    MixUtils.install(this);
  }
}
