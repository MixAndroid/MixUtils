package me.wenlong.mixutils.http;

import android.text.TextUtils;

import java.io.IOException;

import me.wenlong.mixutils.util.SpUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * des   : Token过滤器
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月23日 下午 6:19.
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String authToken = SpUtil.getUser().getAuthToken();
        if (authToken == null || isHasToken(originalRequest)) {
            return chain.proceed(originalRequest);
        }
        Request authorised = originalRequest.newBuilder()
                .header(SpUtil.getUser().getTokenKey(), authToken)
                .build();
        return chain.proceed(authorised);
    }

    private boolean isHasToken(Request request) {
        String tokenValue = request.headers().get(SpUtil.getUser().getTokenKey());
        if (TextUtils.isEmpty(tokenValue)) {
            return false;
        } else {
            return true;
        }
    }
}