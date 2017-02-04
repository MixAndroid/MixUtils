package me.guowenlong.mixutils.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import me.guowenlong.mixutils.entity.User;
import me.guowenlong.mixutils.mvp.BaseActivity;

/**
 * des   : Sp工具类
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2017年02月04日 下午 5:51.
 */

public class SpUtil {
    static SharedPreferences prefs;

    public static String getDataByKey(String key) {
        return prefs.getString(key, "");
    }

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
    }

    public static User getUser() {
        return new Gson().fromJson(prefs.getString("user", ""), User.class);
    }

    public static void setUser(User user) {
        prefs.edit().putString("user", new Gson().toJson(user)).commit();
    }
}
