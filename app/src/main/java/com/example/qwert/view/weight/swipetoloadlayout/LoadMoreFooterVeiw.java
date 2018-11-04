package com.example.qwert.view.weight.swipetoloadlayout;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.example.qwert.R;

/*基于SwipeToLoadLayout 加载更多组件封装*/
public class LoadMoreFooterVeiw extends AppCompatTextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public LoadMoreFooterVeiw(Context context) {
        super(context);
    }

    public LoadMoreFooterVeiw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreFooterVeiw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onLoadMore() {
        setText(getContext().getString(R.string.LOADING_MORE));
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int i, boolean b, boolean b1) {
        if (!b) {
            if (i <= -getHeight()) {
                setText(getContext().getString(R.string.RELEASE_TO_LOAD_MORE));
            } else {
                setText(getContext().getString(R.string.SWIPE_TO_LOAD_MORE));
            }
        } else {
            setText(getContext().getString(R.string.LOAD_MORE_RETURNING));
        }
    }

    @Override
    public void onRelease() {
        setText(getContext().getString(R.string.LOAD_MORE_RETURNING));
    }

    @Override
    public void onComplete() {
        setText(getContext().getString(R.string.LOAD_MORE_COMPLETE));
    }

    @Override
    public void onReset() {
        setText("");
    }
}
