package me.guowenlong.mixutils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guowenlong.me.mixutils.R;

/**
 * des   : 描述 author: Administrator email : guowenlong20000@gmail.com time  : 2017年02月05日 下午 9:32.
 */

public class DemoActivity extends Activity {

  @BindView(R.id.btn_start)
  Button btnStart;
  @BindView(R.id.btn_finish)
  Button btnFinish;
  @BindView(R.id.activity_main)
  LinearLayout activityMain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick({R.id.btn_start, R.id.btn_finish})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_start:
        break;
      case R.id.btn_finish:
        break;
    }
  }
}
