package com.example.observer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.database.ContentObserver;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uri=Uri.parse("Content://com.jusle.Provier.stuInfoProvider");
        getContentResolver().registerContentObserver(uri, true, new MyObserver(new Handler()));
    }
    class MyObserver extends ContentObserver{
    	

		public MyObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			Log.i("Life","ContentProvider中数据发生变化");
		}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
