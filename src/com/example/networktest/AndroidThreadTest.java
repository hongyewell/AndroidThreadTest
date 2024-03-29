package com.example.networktest;

import javax.security.auth.PrivateCredentialPermission;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AndroidThreadTest extends Activity implements OnClickListener {
	
	public static final int UPDATE_TEXT = 1;
	
	private TextView text;
	
    private Button changeText;	
    
    private Handler handler = new Handler(){
    	
    	public void handleMessage(Message msg){
    		switch (msg.what) {
			case UPDATE_TEXT:			
				text.setText("hello miying~");
				break;

			default:
				break;
			}
    	}
    	
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.androidthreadtest);
		text = (TextView) findViewById(R.id.text);
		changeText = (Button) findViewById(R.id.change_text);
		changeText.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_text:
			new Thread(new Runnable() {
				
				@Override
				public void run() {
				    Message message = new Message();
				    message.what = UPDATE_TEXT;					
					handler.sendMessage(message);
				}
			}).start();
			break;

		default:
			break;
		}
		
		
	}

}
