package com.example.qwert.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qwert.R;
import com.example.qwert.bean.TestBean;
import com.example.qwert.mvp.contract.RefreshContract;
import com.example.qwert.mvp.presenter.RefreshPresenter;
import com.example.qwert.tools.T;
import com.example.qwert.view.activity.base.BaseActivity;
import com.example.qwert.view.weight.swipetoloadlayout.LoadMoreFooterVeiw;
import com.example.qwert.view.weight.swipetoloadlayout.RefreshHeaderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshActivity extends BaseActivity<RefreshContract.IPresenter> implements RefreshContract.IView, OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.swipe_refresh_header)
    RefreshHeaderView swipeRefreshHeader;
    @BindView(R.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFooterVeiw swipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private List<TestBean> testBeans = new ArrayList<>();

    @Override
    public void onCreaet() {


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
        swipeToLoadLayout.setLoadMoreEnabled(true);
        swipeToLoadLayout.setRefreshEnabled(true);
        swipeToLoadLayout.setOnRefreshListener(this::onRefresh);
        mRecyclerViewAdapter = new RecyclerViewAdapter(R.layout.item_test);
        mRecyclerViewAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        swipeTarget.setAdapter(mRecyclerViewAdapter);
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

    @Override
    public void onLoadDate(List<TestBean> testBean) {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (testBean != null) {
            testBeans.addAll(testBean);
            mRecyclerViewAdapter.setNewData(testBeans);
        }
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {
        T.s("ssssssssssss");
        mPresenter.LoadMoreDate();
    }


    class RecyclerViewAdapter extends BaseQuickAdapter {

        public RecyclerViewAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            TestBean testBean = (TestBean) item;
            helper.setText(R.id.title_name, testBean.getName());
            helper.setText(R.id.title_age, testBean.getAge() + "");
        }
    }
}
