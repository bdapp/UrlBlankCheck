package com.example.urlblankcheck;

import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;

/*  
 * @info   
 * @author GuoJiang 
 * @time 2016年3月29日 下午12:04:33  
 * @version    
 */
public class CheckHttpUrlEmpty {

	public boolean isExist;
	public HttpUrlEmptyListener openUrl;

	public interface HttpUrlEmptyListener {
		public void openHttpUrl(boolean b);
	};

	public void setHttpUrlEmptyListener(HttpUrlEmptyListener openUrl) {
		this.openUrl = openUrl;
	}
	
	
	
}
