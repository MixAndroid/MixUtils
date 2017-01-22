package me.guowenlong.mixutils.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * des   : 描述
 * author: Administrator
 * email : guowenlong20000@gmail.com
 * time  : 2016年11月28日 下午 5:49.
 */

public class ViewUtils {
    public static class ListView {
        /**
         * 设置listview为num个item的高度
         * @param listView
         * @param num
         */
        public static void setListViewHeightBasedOnChildren(android.widget.ListView listView, float num) {
            if (listView == null) return;
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }
            View listItem = listAdapter.getView(0, null, listView);
            listItem.measure(0, 0);
            int totalHeight = (int) (listItem.getMeasuredHeight() * num);
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }

        /**
         * 自动设置listview高度
         * @param listView
         */
        public static void setListViewHeightBasedOnChildren(android.widget.ListView listView) {
            if (listView == null) return;
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }

    /**
     * 获取是否点击到这个view了
     * @param view
     * @param ev
     * @return
     */
    public static boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())) {
            return false;
        }
        return true;
    }
}