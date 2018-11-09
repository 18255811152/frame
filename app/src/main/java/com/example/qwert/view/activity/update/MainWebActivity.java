package com.example.qwert.view.activity.update;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.qwert.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.just.agentweb.AgentWeb;

import java.util.Timer;
import java.util.TimerTask;

public class MainWebActivity extends Activity {
    private String werUrl;
    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    private SpinKitView loadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);
        werUrl = getIntent().getStringExtra("werUrl");
        mLinearLayout = this.findViewById(R.id.container);
        loadView = this.findViewById(R.id.load_view);
        initView(werUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView(String Url) {
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.error_page, R.id.iv_error)
                .createAgentWeb()//
                .ready()
                .go(Url);
        mAgentWeb.getAgentWebSettings().getWebSettings().setJavaScriptEnabled(true);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadView.setVisibility(View.GONE);
        }
    };

    /**
     * 再按一次退出程序
     */
    private static Boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mAgentWeb.handleKeyEvent(keyCode, event)) {
                return true;
            } else if (!isExit) {
                isExit = true;

                Toast.makeText(MainWebActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            } else {
                this.finish();
            }
        }
        return false;
    }
}
