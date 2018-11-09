package com.example.qwert.view.weight.swipetoloadlayout;

import android.content.Context;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.qwert.R;

/*基于SwipeToLoadLayout 对下拉刷新的封装*/
public class RefreshHeaderView extends LinearLayout implements SwipeRefreshTrigger, SwipeTrigger {

    private AppCompatImageView mIv_refresh_headPic;
    private AppCompatTextView mTv_refresh_status;
    private AppCompatTextView mTv_final_time;


    public RefreshHeaderView(Context context) {
        this(context, null, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*初始化组件*/
    private void init() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(getContext(), R.layout.refresh_head_layout, null);
        mIv_refresh_headPic = view.findViewById(R.id.iv_refresh_headPic);
        mTv_refresh_status = view.findViewById(R.id.tv_refresh_status);
        mTv_final_time = view.findViewById(R.id.tv_final_time);
        addView(view, params);
    }

    @Override
    public void onRefresh() {
        mTv_refresh_status.setText(getContext().getString(R.string.REFRESHING));
    }

    @Override
    public void onPrepare() {
        startHeadAnim();
    }

    /*开启动画*/
    private void startHeadAnim() {
        /*使用帧动画进行开启*/
        /*0.2秒转换一次*/
        mIv_refresh_headPic.setImageResource(R.drawable.anim_refresh_head);
        AnimationDrawable ad = (AnimationDrawable) mIv_refresh_headPic.getDrawable();
        ad.start();
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i >= getHeight()) {
                mTv_refresh_status.setText(getContext().getString(R.string.RELEASE_TO_REFRESH));
            } else {
                mTv_refresh_status.setText(getContext().getString(R.string.SWIPE_TO_REFRESH));
            }
        } else {
            mTv_refresh_status.setText(getContext().getString(R.string.REFRESH_RETURNING));
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        stopHeadAnim();
        mTv_refresh_status.setText("刷新完成");
    }

    @Override
    public void onReset() {

    }

    /*停止动画*/
    private void stopHeadAnim() {
        Drawable drawable = mIv_refresh_headPic.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
        mIv_refresh_headPic.setImageResource(R.mipmap.ic_launcher);
    }
}
