package me.guowenlong.mixutils.mvp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.mixsparks.mix.MixSDK;

/**
 * des   : Fragment基类
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年05月31日 上午 11:32.
 */
public abstract class BaseFragment  extends Fragment {

    public View view;
    public Context mContext;
    public SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initContantView(inflater);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mContext = getActivity();
        sp = MixSDK.mSp;
        initView();
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }


    public abstract void initData(Bundle savedInstanceState);

    public abstract void initView();//创建和非隐藏状态时候都会运行此方法

    public abstract View initContantView(LayoutInflater inflater);

}