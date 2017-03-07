package me.wenlong.mixutils;

import android.content.Context;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.facebook.drawee.backends.pipeline.Fresco;

import me.wenlong.mixutils.util.LogUtils;
import me.wenlong.mixutils.util.SpUtil;
import wenlong.me.mixutils.BuildConfig;


/**
 * des   : 描述
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月23日 下午 2:20.
 */
public class MixUtils {
    public static Context mContext;

    public static void install(Context context) {
        mContext = context;
        Fresco.initialize(context);
        LogUtils.allowLog = !BuildConfig.DEBUG;
        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
        SpUtil.init(mContext);
    }
}