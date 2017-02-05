package me.guowenlong.mixutils.demo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guowenlong.me.mixutils.R;

public class MainActivity extends SwipeBackActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }

    @OnClick({R.id.btn_start, R.id.btn_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.btn_finish:
                finish();
                break;
        }
    }


}