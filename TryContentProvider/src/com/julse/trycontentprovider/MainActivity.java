package com.julse.trycontentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MainActivity extends Activity {
	private ListView listView;
//	唯一标识一个contentview
	private Uri uri;
	private ContentResolver resolver;
	private int checkedId;
	private SimpleCursorAdapter sca;
	private String message=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("尝试访问别的程序数据库");
        listView=(ListView) findViewById(R.id.listView1);
        uri=Uri.parse("Content://com.jusle.Provier.stuInfoProvider");
        resolver=getContentResolver();
        Cursor cursor=resolver.query(uri, new String[]{"_id","stuNo","stuName"}, null,null,null);
        sca=new SimpleCursorAdapter(
        		MainActivity.this,
        		R.layout.item,
        		cursor,
        		new String[]{"_id","stuNo","stuName"},
        		new int[]{R.id.textView1,R.id.textView2,R.id.textView3},
        		0
        		);
        listView.setAdapter(sca);
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				checkedId=arg2;
				PopupMenu pm=new PopupMenu(MainActivity.this,listView);
				pm.getMenuInflater().inflate(R.menu.four_opr, pm.getMenu());
				pm.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem arg0) {
						switch (arg0.getItemId()) {
						case R.id.item0:
							
							break;

						default:
							break;
						}
						Log.i("Life", message+"成功");
						return false;
					}
				});
				return false;
			}
		});
    }

			
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
