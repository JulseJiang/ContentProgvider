package com.julse.studentinfomation;

import com.julse.db.DBhelper;
import com.jusle.Provier.StuInfoProvider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private EditText edtStuId,edtStuName;
	private Button btn_insert;
	private ListView listView;
	private DBhelper dBhelper;
	private SQLiteDatabase dbase;
	private String sql="select * from stu;";
	private Cursor cursor;
	private  SimpleCursorAdapter sca;
	private Uri uri=Uri.parse("Content://com.jusle.Provier.stuInfoProvider");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("学生信息共享");
        
        edtStuId=(EditText) findViewById(R.id.editStuId);
        edtStuName=(EditText) findViewById(R.id.editName);
        btn_insert=(Button) findViewById(R.id.button1);
        listView=(ListView) findViewById(R.id.listView1);
        dBhelper=new DBhelper(MainActivity.this, "stu", null, 1);
        dbase=dBhelper.getReadableDatabase();
        cursor=dbase.rawQuery(sql, null);
//        有问题的适配器
       sca=new SimpleCursorAdapter(
        		MainActivity.this,
        		R.layout.activity_item, 
        		cursor,
        		new String[]{"stuNo","stuName"}, 
        		new int[]{R.id.text_id,R.id.text_name},0//flag
        		);

        listView.setAdapter(sca);
        
        btn_insert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				String sql="insert into stu(stuNo,stuName)?,?;";
				String name=edtStuName.getText().toString().trim();
				String id=edtStuId.getText().toString().trim();
				ContentValues cv=new ContentValues();
				cv.put("stuNo", id);
				cv.put("stuName", name);
//				
////				dbase=dBhelper.getReadableDatabase();
//				StuInfoProvider sp=new StuInfoProvider();
//				sp.insert(uri, cv);
//				cursor=sp.query(uri, 
//						new String[]{"stuNo","stuName"} , null,null,null);
				dbase=dBhelper.getReadableDatabase();
				dbase.insert("stu", null, cv);
				cursor=dbase.rawQuery(sql, null);
				
				sca.notifyDataSetChanged();
				Log.i("Life", "插入成功");
				listView.setAdapter(sca);
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
