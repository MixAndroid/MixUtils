package me.guowenlong.mixutils.demo;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guowenlong.me.mixutils.R;

public class MainActivity extends Activity {


  @BindView(R.id.btn_start)
  Button btnStart;
  @BindView(R.id.btn_finish)
  Button btnFinish;
  @BindView(R.id.webview)
  WebView webview;
  @BindView(R.id.activity_main)
  LinearLayout activityMain;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    getWindow().getDecorView().setBackgroundDrawable(null);
    ButterKnife.bind(this);
  }

  @OnClick({R.id.btn_start, R.id.btn_finish})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.btn_start:
        break;
      case R.id.btn_finish:
        break;
    }
  }
}