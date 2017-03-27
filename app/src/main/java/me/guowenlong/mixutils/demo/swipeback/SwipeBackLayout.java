package me.guowenlong.mixutils.demo.swipeback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import guowenlong.me.mixutils.R;

/**
 * des   : 描述
 * author: Administrator
 * email : guowenlong20000@gmail.com
 * time  : 2017年03月15日 下午 11:46.
 */

public class SwipeBackLayout extends FrameLayout {

  private ViewDragHelper mViewDragHelper;
  private int draggingOffset;
  private int horizontalDragRange;
  private SwipeListener mSwipeListener;
  private boolean edgeTouched;
  private static final int MIN_FLING_VELOCITY = 400; // dips per second

  public SwipeBackLayout(Context context) {
    super(context);
    mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
    final float density = getResources().getDisplayMetrics().density;
    final float minVel = MIN_FLING_VELOCITY * density;
    mViewDragHelper.setMinVelocity(minVel);
  }

  public SwipeBackLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallBack());
    final float density = getResources().getDisplayMetrics().density;
    final float minVel = MIN_FLING_VELOCITY * density;
    mViewDragHelper.setMinVelocity(minVel);
  }

  public void setmSwipeListener(SwipeListener mSwipeListener) {
    this.mSwipeListener = mSwipeListener;
  }

  //滑动时松手后以一定速率继续自动滑动下去并逐渐停止，类似于扔东西 或者 松手后自动滑动到指定位置
  @Override
  public void computeScroll() {
    if (mViewDragHelper.continueSettling(true)) {
      postInvalidate();
    }
  }

  //拦截事件
  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return mViewDragHelper.shouldInterceptTouchEvent(ev);
  }

  //处理事件
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    mViewDragHelper.processTouchEvent(event);
    return true;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (getChildCount() > 1) {
      throw new IllegalStateException("SwipeBackLayout must contains only one direct child.");
    }

    if (getChildCount() > 0) {
      int measureWidth = MeasureSpec
          .makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
              MeasureSpec.EXACTLY);
      int measureHeight = MeasureSpec
          .makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),
              MeasureSpec.EXACTLY);
      getChildAt(0).measure(measureWidth, measureHeight);
    }
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    int width = getMeasuredWidth();
    int height = getMeasuredHeight();
    if (getChildCount() == 0) {
      return;
    }

    View child = getChildAt(0);

    int childWidth = width - getPaddingLeft() - getPaddingRight();
    int childHeight = height - getPaddingTop() - getPaddingBottom();
    int childLeft = getPaddingLeft();
    int childTop = getPaddingTop();
    int childRight = childLeft + childWidth;
    int childBottom = childTop + childHeight;
    child.layout(childLeft, childTop, childRight, childBottom);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    horizontalDragRange = w;
  }

  @Override
  protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
    boolean ret = super.drawChild(canvas, child, drawingTime);
    if (draggingOffset > 0 && mViewDragHelper.getViewDragState() != ViewDragHelper.STATE_IDLE) {
      drawShadow(canvas, child);
    }
    return ret;
  }

  private Drawable mShadowLeft = getResources().getDrawable(R.drawable.shadow_left);
  private Rect mTmpRect = new Rect();
  private float mScrollPercent;

  private void drawShadow(Canvas canvas, View child) {
    final Rect childRect = mTmpRect;
    child.getHitRect(childRect);
    mShadowLeft.setBounds(childRect.left - mShadowLeft.getIntrinsicWidth(), childRect.top,
        childRect.left, childRect.bottom);
    mShadowLeft.setAlpha((int) ((1 - mScrollPercent) * 255));
    mShadowLeft.draw(canvas);
  }

private class ViewDragHelperCallBack extends ViewDragHelper.Callback {

  private float persent;

  @Override
  public boolean tryCaptureView(View child, int pointerId) {
    edgeTouched = mViewDragHelper.isEdgeTouched(ViewDragHelper.EDGE_LEFT, pointerId);
    if (edgeTouched) {
      mSwipeListener.onEdgeTouch();
    }
    boolean b = mViewDragHelper.checkTouchSlop(ViewDragHelper.DIRECTION_HORIZONTAL, pointerId);
    return edgeTouched & b;
  }

  @Override
  public int getViewVerticalDragRange(View child) {
    return super.getViewVerticalDragRange(child);
  }

  @Override
  public int getViewHorizontalDragRange(View child) {
    return 1;
  }

  @Override
  public int clampViewPositionVertical(View child, int top, int dy) {
    return super.clampViewPositionVertical(child, top, dy);
  }

  @Override
  public int clampViewPositionHorizontal(View child, int left, int dx) {
    return Math.min(child.getWidth(), Math.max(left, 0));
  }

  @Override
  public void onViewDragStateChanged(int state) {
    super.onViewDragStateChanged(state);
    if (draggingOffset == horizontalDragRange) {
      finish();
    }
  }

  @Override
  public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
    draggingOffset = Math.abs(left);
    persent = Math.abs((float) left / getWidth());
    mScrollPercent = Math.abs((float) left / (getWidth() + mShadowLeft.getIntrinsicWidth()));
    invalidate();
    mSwipeListener.onScroll(Math.abs((float) left / (getWidth())), left);
  }

  @Override
  public void onViewReleased(View releasedChild, float xvel, float yvel) {
    if (draggingOffset == 0) {
      return;
    }
    if (draggingOffset == horizontalDragRange) {
      return;
    }
    invalidate();
    if (persent > 0.25f && edgeTouched) {
      smoothScrollToX(horizontalDragRange);
    } else {
      smoothScrollToX(0);
    }
  }

}

  private void smoothScrollToX(int finalLeft) {
    if (mViewDragHelper.settleCapturedViewAt(finalLeft, 0)) {
      ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
    }
  }

  private void finish() {
    Activity act = (Activity) getContext();
    act.finish();
  }
}