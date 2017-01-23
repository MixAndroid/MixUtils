package me.guowenlong.mixutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import me.guowenlong.mixutils.entity.User;
import me.guowenlong.mixutils.util.LogUtils;
import wenlong.me.mixutils.BuildConfig;


/**
 * des   : 描述
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月23日 下午 2:20.
 */
public class MixUtils {
    public static Context mContext;
    public static SharedPreferences mSp;

    public static void install(Context context) {
        mContext = context;
        Fresco.initialize(context);
        mSp = PreferenceManager.getDefaultSharedPreferences(context);
        if (BuildConfig.DEBUG) LogUtils.allowLog = false;
    }

    public static User getUser() {
        return new Gson().fromJson(mSp.getString("user", ""), User.class);
    }

    public static void setUser(User user) {
        MixUtils.mSp.edit().putString("user", new Gson().toJson(user)).commit();
    }
}