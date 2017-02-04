package me.guowenlong.mixutils.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Window;

import butterknife.ButterKnife;
import me.guowenlong.mixutils.util.MvpUtils;
import me.guowenlong.mixutils.util.SpUtil;
import wenlong.me.mixutils.R;

/**
 * des   : BaseActivity
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年08月29日 下午 4:44.
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends Activity {
    public T mPresenter;
    public E mModel;
    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        setContentView(getContentView(savedInstanceState));
        ButterKnife.bind(this);
        mPresenter = MvpUtils.getT(this, 0);
        mModel = MvpUtils.getT(this, 1);
        this.initView(savedInstanceState);
        this.initData();
        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
    }

    public void reload() {
        AppCompatDelegate.setDefaultNightMode(SpUtil.isNight() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }

    /**
     * 初始主布局
     */
    protected abstract int getContentView(Bundle savedInstanceState);

    /**
     * 初始化布局
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化一些数据
     */
    protected abstract void initData();
}