package com.martin.fruit;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.martin.fruit.adapter.MyBaseAdapter;
import com.martin.fruit.bean.Fruit;
import com.martin.fruit.utils.DbManger;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private SQLiteDatabase db;
//    private Context mContext;
    private int totalNum; // 表示当前控件加载数据的总条目
    private int pageSize = 20; // 表示每页展示数据的条目
    private int pageNum; // 表示总页码
    private int currentPage = 1; // 当前页码
    private List<Fruit> totalList; // 表示数据
    private MyBaseAdapter mAdapter;
    private boolean isDivPage;

    public static final String PACKAGE_NAME = "com.martin.fruit";
    private static final String DB_NAME = "data.db3";
    private static final String TABLE_NAME = "Content";

//    public static final String DB_PATH = "/data"
//            + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;
//    public void openDatabase(){
//        this.db = this.openDatabase(DB_PATH + "/" + DB_NAME);
//    }
//    private SQLiteDatabase openDatabase(String dbfile){
//        try {
//            File file = new File(dbfile);
//            if (!file.exists()) {
//                InputStream is = mContext.getResources().openRawResource(R.raw.data);
//                FileOutputStream fos = new FileOutputStream(dbfile);
//                byte[] buffer = new byte[1024];
//                int count = 0;
//                while ((count = is.read(buffer)) > 0) {
//                    fos.write(buffer, 0, count);
//                    fos.flush();
//                }
//                fos.close();
//                is.close();
//            }
//            db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
//            return db;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    private String GetDataBasePath(Context context) {
//
//        String packageName = context.getPackageName();
//        //Log.i("PackName", packageName);
////         String DB_PATH = String.format("/data/data/%1$s/databases/", packageName);
////        String DB_PATH = CommonData.baseDir + File.separator + DB_NAME;
//
//        if ((new File(DB_PATH)).exists() == false) {
//            try {
//                // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
//                File f = new File(DB_PATH);
//                // 如 database 目录不存在，新建该目录
//                if (!f.exists()) {
//                    f.mkdir();
//                }
//                // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
//                InputStream is = context.getAssets().open(DB_NAME);
//                // 输出流
//                OutputStream os = new FileOutputStream(DB_PATH);
//                // 文件写入
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = is.read(buffer)) > 0) {
//                    os.write(buffer, 0, length);
//                }
//                // 关闭文件流
//                os.flush();
//                os.close();
//                is.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return DB_PATH;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "data.db3";
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
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
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                    if (currentPage < pageNum){
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
    }

}
