package com.example.qwert.view.weight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qwert.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeadTitleView extends RelativeLayout {


    @BindView(R.id.left_btn)
    FrameLayout leftBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.fl_right_click)
    FrameLayout flRightClick;
    private Context mContext;


    public HeadTitleView(Context context) {
        super(context);
        InitView(context);
    }

    public HeadTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitView(context);
    }

    public HeadTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitView(context);
    }


    private void InitView(Context mContext) {
        this.mContext = mContext;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_headview, null);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.left_btn)
    public void onViewClicked() {
        if (mContext instanceof Activity) {
            ((Activity) mContext).finish();
        }
    }


    @SuppressLint("WrongConstant")
    public void setHeadTitleName(String Name, boolean HasLeftBtn) {
        titleName.setText(Name);
        if (HasLeftBtn) {
            leftBtn.setVisibility(0);
        } else {
            leftBtn.setVisibility(8);
        }

    }

}
