package com.xl.sqlitedemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xl.sqlitedemo.Book;
import com.xl.sqlitedemo.R;
import com.xl.sqlitedemo.dbutil.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class DBLearnActivity extends AppCompatActivity implements View.OnClickListener {

    private MySQLiteHelper mMySQLiteHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b_learn);
        Button create=findViewById(R.id.create_button);
        Button insert=findViewById(R.id.insert_button);
        Button delete=findViewById(R.id.delete_button);
        Button update=findViewById(R.id.update_button);
        Button query=findViewById(R.id.query_button);
        create.setOnClickListener(this);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
        mMySQLiteHelper=new MySQLiteHelper(this,"BookStore.db",null,2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_button:
                mDatabase = mMySQLiteHelper.getReadableDatabase();
                break;
            case R.id.insert_button:
                insertData();
                break;
            case R.id.delete_button:
                deleteData();
                break;
            case R.id.update_button:
                updataData();
                break;
            case R.id.query_button:
                queryData();
                mDatabase.needUpgrade(2);
                break;
            default:
        }
    }

    private void queryData() {
        Cursor cursor = mDatabase.rawQuery("select* from book", null);

        Cursor cursor1=mDatabase.rawQuery("select * from book where book_name='水浒传修改后'",null);

        Cursor cursor2=mDatabase.query("book",null,null,null,null,null,null);

        Cursor cursor3=mDatabase.query("book",null,"book_name=?",new String[]{"水浒传修改后"},null,null,null);

        List<Book> datas=new ArrayList<>();
        while (cursor.moveToNext()){
            Book book=new Book();
            String bookName=cursor.getString(cursor.getColumnIndex("book_name"));
            double price=cursor.getDouble(cursor.getColumnIndex("price"));
            book.setBook_name(bookName);
            book.setPrice(price);
            datas.add(book);
        }
        Log.d("book","查询到的数据："+datas.size());
    }

    private void updataData() {
        mDatabase.execSQL("update book set book_name='西游记修改后'where book_name='西游记'");

        ContentValues contentValues=new ContentValues();
        contentValues.put("book_name","水浒传修改后");
        contentValues.put("price",66.6);
        mDatabase.update("book",contentValues,"book_name=?",new String[]{"水浒传"});
    }

    private void deleteData() {
        mDatabase.execSQL("delete from book where book_name='三国演义'");

        mDatabase.delete("book","price=?",new String[]{"122"});
    }

    private void insertData() {
        mDatabase.execSQL("insert into book(book_name,price) values('三国演义',39.8)");
        mDatabase.execSQL("insert into book(book_name,price) values('水浒传',44.4)");

        ContentValues contentValues=new ContentValues();
        contentValues.put("book_name","西游记");
        contentValues.put("price",62.5);
        mDatabase.insert("book",null,contentValues);

        ContentValues contentValues1=new ContentValues();
        contentValues1.put("book_name","红楼梦");
        contentValues1.put("price",122);
        mDatabase.insert("book",null,contentValues1);

    }
}