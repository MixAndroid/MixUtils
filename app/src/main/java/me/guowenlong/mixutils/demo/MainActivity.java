package me.guowenlong.mixutils.demo;


import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guowenlong.me.mixutils.R;
import me.guowenlong.mixutils.demo.swipeback.SwipeBackLayout;
import me.guowenlong.mixutils.demo.swipeback.SwipeListener;
import me.guowenlong.mixutils.demo.swipeback.SwipeUtils;
import me.wenlong.mixutils.util.LogUtils;

public class MainActivity extends Activity {

  @BindView(R.id.btn_start)
  Button btnStart;
  @BindView(R.id.btn_finish)
  Button btnFinish;
  @BindView(R.id.activity_main)
  LinearLayout activityMain;
  private SwipeBackLayout swipe;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    getWindow().getDecorView().setBackgroundDrawable(null);
    ButterKnife.bind(this);
  }

//  private View getContainer() {
//    RelativeLayout container = new RelativeLayout(this);
//    swipeBackLayout = new SwipeBackLayout(this);
//    ImageView ivShadow = new ImageView(this);
//    ivShadow.setBackgroundColor(getResources().getColor(R.color.tran));
//    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//    container.addView(ivShadow, params);
//    container.addView(swipeBackLayout);
//    return container;
//  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ViewGroup decor = (ViewGroup) getWindow().getDecorView();
    TypedArray a = getTheme().obtainStyledAttributes(new int[]{
        android.R.attr.windowBackground
    });
    int background = a.getResourceId(0, 0);
    a.recycle();
    ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
    decorChild.setBackgroundResource(background);
    decor.removeView(decorChild);
    swipe = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.aaa, null);
    swipe.setmSwipeListener(new SwipeListener() {
      @Override
      public void onEdgeTouch() {

      }

      @Override
      public void onScroll(float f, int i) {
        LogUtils.e("f:" + f + ",i:" + i);
        SwipeUtils.getSwipeBackLayout(swipe).setX(Math.min(-500 * Math.max(1 - f, 0) + 40, 0));
        if (f == 0) {
          SwipeUtils.getSwipeBackLayout(swipe).setX(0);
        }
      }
    });
    swipe.addView(decorChild);
    decor.addView(swipe);
    SwipeUtils.onCreate(swipe);
//    setDragEdge(SwipeBackLayout.DragEdge.LEFT);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    SwipeUtils.onDestory(swipe);
  }

  @OnClick({R.id.btn_start, R.id.btn_finish})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_start:
        startActivity(new Intent(this, MainActivity.class));
        break;
      case R.id.btn_finish:
        finish();
        break;
    }
  }
}