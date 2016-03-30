package com.example.urlblankcheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Context mContext;
	private String TAG = "MainActivity";

	private EditText mEdit;
	private TextView mText;
	private WebView mWebView;

	private ProgressDialog loadingDialog;

	private String openUrl;

	private Handler mHandler;
	public static final int FALSECODE = 0;
	public static final int TRUECODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		initUi();

		loadingDialog = showDialog(mContext);

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
				loadingDialog.show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				loadingDialog.cancel();
				;
			}

		});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.click_text:
			openUrl = mEdit.getText().toString().trim();
			if (openUrl.length() > 0) {
				new CheckConnectStatus(mHandler, openUrl);
			}
			break;

		default:
			break;
		}
	}

	private ProgressDialog showDialog(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setMessage("加载中...");
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		return dialog;
	}

	class MyHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == FALSECODE) {
				mWebView.loadUrl("");
			} else if (msg.what == TRUECODE) {
				mWebView.loadUrl(openUrl);
			}

		}

	}

}
