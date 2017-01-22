package me.guowenlong.mixutils.http;

import java.io.IOException;

import me.mixsparks.mix.MixSDK;
import me.mixsparks.mix.util.LogUtils;
import me.mixsparks.mix.util.NetWorkUtil;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * des   : 请求缓存拦截器
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月23日 上午 11:38.
 */
public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkUtil.isNetConnected(MixSDK.mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            LogUtils.d("Okhttp no network");
        }

        Response originalResponse = chain.proceed(request);
        if (NetWorkUtil.isNetConnected(MixSDK.mContext)) {//有网的时候读接口上的@Headers里的配置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .addHeader("Content-Type", "application/json")
                    .removeHeader("Pragma")
                    .build();
        } else {//没网的时候读取Cache
            int maxStale = 60 * 60 * 24 * 14;//2周
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale="+maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}