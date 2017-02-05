package me.wenlong.mixutils.util;

import java.lang.reflect.ParameterizedType;

/**
 * des   : Mvp库的工具类
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月29日 下午 4:44.
 */
public class MvpUtils {
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}