package com.xl.greendao;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xl.greendao.bean.Book;
import com.xl.greendao.bean.BookDao;
import com.xl.greendao.bean.DaoMaster;
import com.xl.greendao.bean.DaoSession;
import com.xl.greendao.bean.Student;
import com.xl.greendao.bean.StudentDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {

    @BindView(R.id.insert)
    Button mInsert;
    @BindView(R.id.delete)
    Button mDelete;
    @BindView(R.id.update)
    Button mUpdate;
    @BindView(R.id.query)
    Button mQuery;
    private Database mDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private StudentDao mStudentDao;
    private BookDao mBookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        getStuDao();

    }

    private void getStuDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(GreenDaoActivity.this, "xl.db");
//        mDatabase = devOpenHelper.getEncryptedWritableDb("yty365");
        mDatabase=devOpenHelper.getWritableDb();
//         DaoMaster保存数据库对象（SQLiteDatabase）并管理特定模式的Dao类（而不是对象）。它具有静态方法来创建表或将它们删除。其内部类OpenHelper和DevOpenHelper是在SQLite数据库中创建模式的SQLiteOpenHelper实现。
        mDaoMaster = new DaoMaster(mDatabase);
//         管理特定模式的所有可用Dao对象，您可以使用其中一个getter方法获取。DaoSession还为实体提供了一些通用的持久性方法，如插入，加载，更新，刷新和删除。最后，DaoSession对象也跟踪一个身份范围。有关更多详细信息，[请点击查看会话文档](http://greenrobot.org/greendao/documentation/sessions/)。
        mDaoSession = mDaoMaster.newSession();
//        据访问对象（Dao）持续存在并查询实体。对于每个实体，GreenDao生成一个Dao，它比DaoSession有更多的持久化方法，例如：count，loadAll和insertInTx。
        mStudentDao = mDaoSession.getStudentDao();

        mBookDao = mDaoSession.getBookDao();
    }

    @OnClick({R.id.insert,R.id.insert_list, R.id.delete, R.id.update, R.id.query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                Student student=new Student(null,"1","jack","male","97");
                Student student2=new Student(null,"2","toy","female","92");
                Student student3=new Student(null,"3","lecy","male","87");
                long insert = mStudentDao.insertOrReplace(student);
                mStudentDao.insertOrReplace(student2);
                mStudentDao.insertOrReplace(student3);
                Log.d("db_stu","数据"+mStudentDao.loadAll().size());
                break;
            case R.id.insert_list:
                List<Book> books=new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Book book=new Book(null,"book"+i,i*6.5);
                    books.add(book);
                }
                mBookDao.insertInTx(books);
                Log.d("db_book","数据"+mBookDao.loadAll().size());
                break;
            case R.id.delete:
                mStudentDao.queryBuilder().where(StudentDao.Properties.StuName.eq("toy")).buildDelete().executeDeleteWithoutDetachingEntities();
//                mStudentDao.delete(new Student());
//                mStudentDao.deleteAll();
                Log.d("delete",""+mStudentDao.loadAll().size());
                break;
            case R.id.update:
                Student jack1 = mStudentDao.queryBuilder().where(StudentDao.Properties.StuName.eq("jack")).build().unique();
                if (jack1!=null){
                    jack1.setStuName("jack_more");
                    mStudentDao.update(jack1);
                }
                List<Student> listt = mStudentDao.queryBuilder().list();
                String searchAllIn = "";
                for (int i = 0; i < listt.size(); i++) {
                    Student stu = listt.get(i);
                    searchAllIn += "id：" + stu.getStuId() + "编号：" + stu.getStuNo() + "姓名：" + stu.getStuName() + "性别：" + stu.getStuSex() + "成绩：" + stu.getStuScore() + "\n";
                }
                Log.d("update",""+searchAllIn);
                break;
            case R.id.query:
                List<Student> list = mStudentDao.queryBuilder().list();
                String searchAllInfo = "";
                for (int i = 0; i < list.size(); i++) {
                    Student stu = list.get(i);
                    searchAllInfo += "id：" + stu.getStuId() + "编号：" + stu.getStuNo() + "姓名：" + stu.getStuName() + "性别：" + stu.getStuSex() + "成绩：" + stu.getStuScore() + "\n";
                }
                String bookall="";
                List<Book> bookList = mBookDao.queryBuilder().list();
                for (int i = 0; i < bookList.size(); i++) {
                    Book book=bookList.get(i);
                    bookall+="id:"+book.getId()+"书名:"+book.getName()+"价格:"+book.getPrice()+"\n";
                }

                List<Student> jack = mStudentDao.queryBuilder().where(StudentDao.Properties.StuName.eq("jack")).list();
                for (int i = 0; i < jack.size(); i++) {
                    Log.d("where_stu",""+jack.get(i).getStuName()+jack.get(i).getStuScore());
                }

                List<Book> list1 = mBookDao.queryBuilder().where(BookDao.Properties.Price.lt(46)).list();
                String aaa="";
                for (int i = 0; i < list1.size(); i++) {
                    Book book=list1.get(i);
                    aaa+=book.getId()+book.getName()+book.getPrice()+"\n";
                }
                Log.d("lt_book",""+aaa);


                Log.d("stu_all",""+searchAllInfo);
                Log.d("book_all",""+bookall);
                break;
        }
    }
}