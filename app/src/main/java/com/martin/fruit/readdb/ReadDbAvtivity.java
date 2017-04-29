package com.martin.fruit.readdb;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.martin.fruit.R;
import com.martin.fruit.adapter.MyBaseAdapter;
import com.martin.fruit.bean.Fruit;
import com.martin.fruit.utils.DbManger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Martin on 2017/4/29 0029.
 */

public class ReadDbAvtivity extends AppCompatActivity {
    private ListView mListView;
    private int totalNum; // 表示当前控件加载数据的总条目
    private int pageSize = 20; // 表示每页展示数据的条目
    private int pageNum; // 表示总页码
    private int currentPage = 1; // 当前页码
    private List<Fruit> totalList; // 表示数据
    private MyBaseAdapter mAdapter;
    private boolean isDivPage;
    private Context mContext;
    private SwipeRefreshLayout swipeRefresh;

    public static final String PACKAGE_NAME = "com.martin.fruit";
    private static final String DB_NAME = "data.db3";
    private static final String TABLE_NAME = "Content";

    public static final String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"
            + PACKAGE_NAME; // 获取存储位置地址
    private static SQLiteDatabase  database;
    static String databaseFilename = DATABASE_PATH + "/" + DB_NAME;
    public static SQLiteDatabase openDatabase(Context context)
    {
        try
        {
//          String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
            File dir = new File(DATABASE_PATH);
            if (!dir.exists())
            {
                dir.mkdir();//新建文件
            }
            if (!(new File(databaseFilename)).exists())
            {
                InputStream is = context.getResources().openRawResource(
                        R.raw.data);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, count);
                }

                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(databaseFilename,
                    null);
            return database;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv);
        String path = databaseFilename;
        Log.i("Main", path);
        final SQLiteDatabase db = openDatabase(this);
        totalNum = DbManger.getDataCount(db, TABLE_NAME);
        pageNum = (int) Math.ceil(totalNum / (double) pageSize);
        if (currentPage == 1){
            totalList = DbManger.getListByCurrentPage(db, TABLE_NAME, currentPage, pageSize);
        }
        mAdapter = new MyBaseAdapter(this, totalList);
        mListView.setAdapter(mAdapter);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
                    if (currentPage < pageNum) {
                        currentPage++;
                        totalList.addAll(DbManger.getListByCurrentPage(db, TABLE_NAME, currentPage, pageSize));
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isDivPage = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = totalList.get(position);
                Intent intent = new Intent(ReadDbAvtivity.this, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_INTRODUCE, fruit.get介绍());
//                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.get缩略图());
                startActivity(intent);
            }
        });
    }

}