package app.mrquan.datastorage.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.mrquan.datastorage.pojo.Book;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE = "create table book( id varchar(20) primary key,name varchar(20),price REAL)";
    private Cursor cursor;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized int add(Book book){//插入数据
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",book.getId());
        contentValues.put("name",book.getName());
        contentValues.put("price",book.getPrice());
        long m = database.insert("book",null, contentValues);
        this.close();
        return m==-1 ? 0:1;
    }

    public synchronized int delete(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("book","id = ?",new String[]{id});
    }

    public List<Book> list(){
        List<Book> books = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query("book",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            Book book;
            do{
                //遍历Cursor对象，取出数据并打印
                book = new Book(
                        cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getDouble(cursor.getColumnIndex("price")));
//                Log.d("数据", String.valueOf(book));
                books.add(book);
            }while(cursor.moveToNext());
        }
        return books;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
            cursor = null;
        }
        super.close();
    }
}
