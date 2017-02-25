package me.wenlong.mixutils.mvp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import butterknife.ButterKnife;

/**
 * des   : Fragment基类
 * author: Gavin
 * email : guowenlong20000@gmail.com
 * time  : 2016年05月31日 上午 11:32.
 */
public abstract class BaseFragment extends Fragment {

    public View view;
    public Context mContext;
    protected SharedPreferences sp;
    protected HashMap<String, Object> mFromMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initContantView(inflater);
        ButterKnife.bind(this, view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mContext = getActivity();
        sp = mContext.getSharedPreferences("config", mContext.MODE_PRIVATE);
        initView();
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    public abstract void initData(Bundle savedInstanceState);

    public abstract void initView();

    public abstract View initContantView(LayoutInflater inflater);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}