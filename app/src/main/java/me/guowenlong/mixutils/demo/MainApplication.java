package me.guowenlong.mixutils.demo;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hz51390 on 2017/3/7.
 */

public class MainApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
  }
}
