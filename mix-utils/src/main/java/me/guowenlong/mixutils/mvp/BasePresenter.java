package me.guowenlong.mixutils.mvp;

import android.content.Context;

import me.mixsparks.mix.rxjava.RxManager;

/**
 * des   : BasePresenter
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月29日 下午 4:44.
 */
public abstract class BasePresenter<M, T> {
    public Context context;
    public M mModel;
    public T mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
        mRxManager.clear();
    }

}
