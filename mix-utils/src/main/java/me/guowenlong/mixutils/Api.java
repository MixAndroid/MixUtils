package me.guowenlong.mixutils;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.mixsparks.mix.http.HttpCacheInterceptor;
import me.mixsparks.mix.http.TokenInterceptor;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wenlong.me.mixutils.BuildConfig;

/**
 * des   : api
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月19日 下午 3:30.
 */
public class Api {
    public Retrofit retrofit;

    public static class Builder {
        private File cachefile;
        private String baseUrl;
        private long cachelength;
        private long readTimeout;
        private long connectTimeout;
        private Api.LogLevel logLevel;
        private TimeUnit timeUnit;
        private boolean isCache;
        private boolean isAddTokenInterceptor;
        private List<Interceptor> interceptors;

        public Builder() {
            this.cachelength = 1024 * 1024 * 50;
//            this.cachefile = new File(MixSDK.mContext.getCacheDir(), "okhttp_cache");
            this.isCache = false;
            this.readTimeout = 180_000;
            this.connectTimeout = 180_000;
            this.logLevel = LogLevel.body;
            this.timeUnit = TimeUnit.MILLISECONDS;
            this.isAddTokenInterceptor = false;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setLogLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public Builder setCache(File cachefile, long cachelength) {
            this.isCache = true;
            this.cachefile = cachefile;
            this.cachelength = cachelength;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }

        public Api build() {
            return new Api(this);
        }
    }

    private Api(Builder builder) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                .readTimeout(builder.readTimeout, builder.timeUnit)
                .connectTimeout(builder.connectTimeout, builder.timeUnit)
                .addInterceptor(getHttpLoggingInterceptor(builder.logLevel));
        if (builder.isCache) {
            okBuilder = okBuilder.cache(new Cache(builder.cachefile, builder.cachelength))
                    .addNetworkInterceptor(new HttpCacheInterceptor());
        }

        if (builder.interceptors != null) {
            for (int i = 0; i < builder.interceptors.size(); i++) {
                okBuilder.addInterceptor(builder.interceptors.get(i));
            }
        }

        if (builder.isAddTokenInterceptor) {
            okBuilder.addInterceptor(new TokenInterceptor());
        }

        retrofit = new Retrofit.Builder()
                .client(okBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(builder.baseUrl)
                .build();
    }

    public HttpLoggingInterceptor getHttpLoggingInterceptor(LogLevel logLevel) {
        HttpLoggingInterceptor.Level level;
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.NONE;
            return new HttpLoggingInterceptor().setLevel(level);
        }
        switch (logLevel) {
            case body:
                level = HttpLoggingInterceptor.Level.BODY;
                break;
            case header:
                level = HttpLoggingInterceptor.Level.HEADERS;
                break;
            case basic:
                level = HttpLoggingInterceptor.Level.BASIC;
                break;
            default:
                level = HttpLoggingInterceptor.Level.BODY;
                break;
        }
        return new HttpLoggingInterceptor().setLevel(level);
    }


    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public enum LogLevel {
        body, basic, header, none
    }


}