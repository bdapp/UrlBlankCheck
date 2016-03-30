package com.example.urlblankcheck;  

import java.net.HttpURLConnection;
import java.net.URL;

import android.R.string;
import android.os.Handler;
import android.os.Message;

/*  
 * @info   
 * @author GuoJiang 
 * @time 2016年3月29日 下午3:19:01  
 * @version    
 */
public class CheckConnectStatus {

	
	public String url;
	public Handler handler;
	
	public void checkUrlIsExist(final String url) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
					int code = connection.getResponseCode();
					if (code == 200) {
						handler.sendEmptyMessage(MainActivity.TRUECODE);
					} else {
						handler.sendEmptyMessage(MainActivity.FALSECODE);
					}

				} catch (Exception e) {
				}
			}
		}).start();
	}


	public CheckConnectStatus(Handler handler, String url) {
		this.handler = handler;
		this.url = url;
		checkUrlIsExist(url);
	}
}
  