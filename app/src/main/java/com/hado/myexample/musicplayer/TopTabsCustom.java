package com.hado.myexample.musicplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

import com.hado.myexample.R;
import com.hado.myexample.util.DebugLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngo Hado on 17-Jul-16.
 */
public class TopTabsCustom extends RelativeLayout implements View.OnClickListener {

    private static final int DURATION_DEFAULT = 250;
    private static final int EXPAND_HEIGHT = 20;
    private static final int COLLAPSE_HEIGHT = -20;

    public RelativeLayout layoutTabs;

    public View lineView;

    private View tabSelected;
    private List<View> tabsView = new ArrayList<>();

    private OnTabSelectedChange tabChangeListener;

    public TopTabsCustom(Context context) {
        super(context);
    }

    public TopTabsCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopTabsCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView() {
        layoutTabs = (RelativeLayout) findViewById(R.id.layout_tabs);
        lineView = findViewById(R.id.line);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        View childView;
        for (int i = 0; i < layoutTabs.getChildCount(); i++) {
            childView = layoutTabs.getChildAt(i);
            childView.setTag(i);
            childView.setOnClickListener(this);
            tabsView.add(childView);
        }
        tabSelected = tabsView.get(0);
    }

    public void setTabChangeListener(OnTabSelectedChange tabChangeListener) {
        this.tabChangeListener = tabChangeListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() == tabSelected.getTag())
            return;

        View viewBefore = tabSelected;
        int positionBefore = (int) viewBefore.getTag();

        if (tabSelected != null) {
            tabSelected.startAnimation(new ResizeAnimation(tabSelected, COLLAPSE_HEIGHT));
        }

        tabSelected = v;
        tabSelected.startAnimation(new ResizeAnimation(tabSelected, EXPAND_HEIGHT));
        switch ((int) tabSelected.getTag()) {
            case 0:
                lineView.setBackgroundResource(R.color.tab_1);
                break;
            case 1:
                lineView.setBackgroundResource(R.color.tab_2);
                break;
            case 2:
                lineView.setBackgroundResource(R.color.tab_3);
                break;
            case 3:
                lineView.setBackgroundResource(R.color.tab_4);
                break;

        }

        if (tabChangeListener != null)
            tabChangeListener.onTabSelected(viewBefore, tabSelected, positionBefore, (int) tabSelected.getTag());
    }

    public void setTabSelected(int position) {
        if (position < tabsView.size()) {
            onClick(tabsView.get(position));
        } else {
            DebugLog.e(position + " larger than range of tabs size : " + tabsView.size());
        }
    }

    public View getCurrentTab() {
        return tabSelected;
    }

    public int getCurrentTabPosition() {
        return (int) tabSelected.getTag();
    }

    class ResizeAnimation extends Animation implements Animation.AnimationListener {

        View view;
        int heightChange;
        int initHeight;

        public ResizeAnimation(View view, int heightChange) {
            this.view = view;
            this.heightChange = heightChange;
            this.initHeight = view.getLayoutParams().height;
            setDuration(DURATION_DEFAULT);
            setAnimationListener(this);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            view.getLayoutParams().height = (int) (initHeight + heightChange * interpolatedTime);
            view.requestLayout();
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    interface OnTabSelectedChange {
        void onTabSelected(View viewBeforeSelected, View viewSelected, int positionBeforeSelected, int positionSelected);
    }
}