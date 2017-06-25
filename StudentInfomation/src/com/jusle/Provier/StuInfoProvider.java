package com.jusle.Provier;

import com.julse.db.DBhelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class StuInfoProvider extends ContentProvider{
	private DBhelper dBhelper;
	private SQLiteDatabase dbase;
	private Context context;
//	ContentProvider 必须有个构造方法，不然没有实例
	public StuInfoProvider(){
		
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		
		dbase=dBhelper.getReadableDatabase();
		int count=dbase.delete("stu", arg1, arg2);
		getContext().getContentResolver().notifyChange(arg0, null);
		return count;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		dbase=dBhelper.getReadableDatabase();
		
//		ContentValues 用法
//		ContentValues cv=new ContentValues();
//		cv.put("stuNum", "101");
//		cv.put("stuName", "张三");
//		dbase.insert("stu", null, cv);
		
//		 指定字段null可以为空 arg1以键值对存储的记录
		Long id=dbase.insert("stu", null, arg1);
		dbase.close();
//		Uri 返回类型：插入的这条记录对应的uri
		Log.i("Life", "插入成功！");
		return ContentUris.withAppendedId(arg0, id);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dBhelper=new DBhelper(getContext(),"stu.db",null, 1);
		Log.i("Life", "provider创建成功");
		return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		dbase=dBhelper.getReadableDatabase();
		Cursor cursor=dbase.query("stu",
				arg1, 
				arg2, 
				arg3, 
				arg4, 
				null, 
				null);
		Log.i("contentprovider","cusor.getCount:"+cursor.getCount());
		return cursor;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		dbase=dBhelper.getReadableDatabase();
		dbase.update("stu", arg1, arg2, arg3);
		dbase.close();
		return 0;
	}

}
