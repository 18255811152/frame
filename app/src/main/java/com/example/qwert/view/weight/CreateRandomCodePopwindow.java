package com.example.qwert.view.weight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.example.qwert.R;


public class CreateRandomCodePopwindow extends PopupWindow {
    private PopupWindow mPopWindow;
    private Context mContext;

    public CreateRandomCodePopwindow(Context mContext) {
        this.mContext = mContext;
    }

    public CreateRandomCodePopwindow(View contentView) {
        super(contentView);
        mPopWindow = new PopupWindow();
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_rand, null);
    }
}
