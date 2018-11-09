package com.example.qwert.view.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.qwert.R;
import com.example.qwert.mvp.contract.RefreshContract;
import com.example.qwert.mvp.presenter.RefreshPresenter;
import com.example.qwert.view.activity.adapter.TestRefreshAdapter;
import com.example.qwert.view.activity.base.BaseActivity;
import com.example.qwert.view.weight.swipetoloadlayout.LoadMoreFooterVeiw;
import com.example.qwert.view.weight.swipetoloadlayout.RefreshHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshActivity extends BaseActivity<RefreshContract.IPresenter> implements RefreshContract.IView {

    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterVeiw swipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private TestRefreshAdapter mRecyclerViewAdapter;

    @Override
    public void onCreaet() {
        swipeToLoadLayout.setRefreshEnabled(false);
        swipeToLoadLayout.setRefreshEnabled(false);
        swipeToLoadLayout.setLoadMoreEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new RefreshPresenter(this, this);
//        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
//        swipeTarget.setAdapter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }

    @Override
    protected void initDate() {


    }

    @Override
    protected void initEven() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void Log(String t) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
