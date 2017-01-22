package me.guowenlong.mixutils.rxjava;

import me.mixsparks.mix.util.LogUtils;

/**
 * des   : 异常提前统一处理
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月26日 上午 11:54.
 */
public abstract class Observer implements rx.Observer {

    @Override
    public void onError(Throwable t) {
        String throwable = t.toString();
        if (throwable.contains("GaiException")){
            throwable = "设备网络连接没有打开";
        }else if (throwable.contains("SocketTimeoutException")){
            throwable = "网络连接超时";
        }else if (throwable.contains("UnknownHostException")){
            throwable = "主机地址解析错误";
        }else if (throwable.contains("API没有")){
            throwable = "没有找到这个api";
        }
        LogUtils.e(throwable);
    }
}