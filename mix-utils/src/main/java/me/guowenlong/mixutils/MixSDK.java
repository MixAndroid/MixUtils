package me.guowenlong.mixutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import me.mixsparks.mix.entity.User;
import me.mixsparks.mix.util.LogUtils;

/**
 * des   : 描述
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月23日 下午 2:20.
 */
public class MixSDK {
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
        MixSDK.mSp.edit().putString("user", new Gson().toJson(user)).commit();
    }
}