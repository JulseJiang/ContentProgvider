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
//	ContentProvider �����и����췽������Ȼû��ʵ��
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
		
//		ContentValues �÷�
//		ContentValues cv=new ContentValues();
//		cv.put("stuNum", "101");
//		cv.put("stuName", "����");
//		dbase.insert("stu", null, cv);
		
//		 ָ���ֶ�null����Ϊ�� arg1�Լ�ֵ�Դ洢�ļ�¼
		Long id=dbase.insert("stu", null, arg1);
		dbase.close();
//		Uri �������ͣ������������¼��Ӧ��uri
		Log.i("Life", "����ɹ���");
		return ContentUris.withAppendedId(arg0, id);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dBhelper=new DBhelper(getContext(),"stu.db",null, 1);
		Log.i("Life", "provider�����ɹ�");
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
