package com.xl.sqlitedemo.dbutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//创建一个名为“BookStore.db”的数据库，然后在这个数据库中新建一张Book表，表中有 id（主键）、作者、价格、页数和书名等列

public class MySQLiteHelper extends SQLiteOpenHelper {

//    integer表示整型，real表示浮点型，text表示文本类型，blob表示二进制类型

//     id（主键）、作者、价格、页数和书名
    public static final String CREATE_BOOK="create table book(id integer primary key autoincrement ,book_name text, price real)";
    public static final String CREATE_CATEGORY="create table author(id integer primary key autoincrement,author_name text,age integer)";

    private Context mContext;

    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*第一个参数：Context上下文对象。
      第二个参数：为数据库库名。
      第三个参数：允许我们在查询数据的时候返回一个自定义的 Cursor，一般传入null。
      第四各参数：当前数据库最新版本号，常用语更新数据库。
      第五个参数：这个对象用于处理数据库的异常，比较少使用。*/
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        this.mContext=context;
    }

    @SuppressLint("NewApi")
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//      创建数据库
        db.execSQL(CREATE_BOOK);

//        Toast.makeText(mContext, "创建成功!", Toast.LENGTH_SHORT).show();
        Log.d("db","创建成功！");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//      更新数据库
        db.execSQL(CREATE_CATEGORY);
        Log.d("update","数据库更新成功！");
    }
}
