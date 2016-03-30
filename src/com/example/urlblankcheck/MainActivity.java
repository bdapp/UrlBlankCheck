package com.example.urlblankcheck;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.example.urlblankcheck.CheckHttpUrlEmpty.HttpUrlEmptyListener;

public class MainActivity extends Activity implements OnClickListener, HttpUrlEmptyListener {
	private Context mContext;
	private String TAG = "MainActivity";

	private EditText mEdit;
	private TextView mText;
	private WebView mWebView;

	private String openUrl;
	
	private Handler mHandler ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		initUi();

		mHandler = new MyHandler();
	}
	
	
	private void initUi() {
		mEdit = (EditText) findViewById(R.id.url_edit);
		mEdit.setText("http://www.baidu.com");
		
		mText = (TextView) findViewById(R.id.click_text);
		mText.setOnClickListener(this);

		mWebView = (WebView) findViewById(R.id.load_webview);
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);

		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

		});
		

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.click_text:
			CheckConnectStatus check = new CheckConnectStatus(mHandler, mEdit.getText().toString());
			
			break;

		default:
			break;
		}
	}

//	private void checkUrlIsExist(final String url) {
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				try {
//
//					HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//					int code = connection.getResponseCode();
//					if (code == 200) {
////						checkUrl(true);
//						mHandler.sendEmptyMessage(1);
//					} else {
////						checkUrl(false);
//						mHandler.sendEmptyMessage(0);
//					}
//
//				} catch (Exception e) {
//				}
//			}
//		}).start();
//	}
	
	
	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==CheckConnectStatus.FALSECODE) {
				mWebView.loadUrl("");
			} else if (msg.what==CheckConnectStatus.TRUECODE){
				mWebView.loadUrl(openUrl);
			}
			
		}
		
	}

	

	@Override
	public void openHttpUrl(boolean b) {
		// TODO Auto-generated method stub
		if (b) {
			mWebView.loadUrl(openUrl);
		} else {
			mWebView.loadUrl("");
		}

	}

}
