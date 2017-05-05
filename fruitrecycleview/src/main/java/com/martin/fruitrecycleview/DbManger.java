package com.martin.fruitrecycleview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.martin.fruitrecycleview.bean.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 2017/4/29 0029.
 */

public class DbManger {
    /**
     * 根据sql 语句查询获得cursor对象
     * @param db 数据库对象
     * @param sql 查询的sql 语句
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db, String sql, String[] selectionArgs){
        Cursor cursor = null;
        if (db != null){
            cursor =  db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    public static List<Fruit> cursorToList(Cursor cursor){
        List<Fruit> list = new ArrayList<Fruit>();
        while (cursor.moveToNext()){
            String 缩略图 = cursor.getString(cursor.getColumnIndex("缩略图"));
            String 标题 = cursor.getString(cursor.getColumnIndex("标题"));
            String 副标题 = cursor.getString(cursor.getColumnIndex("副标题"));
            String 功效 = cursor.getString(cursor.getColumnIndex("功效"));
            String 禁忌人群 = cursor.getString(cursor.getColumnIndex("禁忌人群"));
            String 介绍 = cursor.getString(cursor.getColumnIndex("介绍"));
            Fruit fruit = new Fruit(缩略图,标题,  副标题, 功效, 禁忌人群, 介绍);
            list.add(fruit);
        }
        return list;
    }

    /**
     * 根据数据库以及数据表名称获取表中数据总条目
     * @param db 数据库对象
     * @param tableName 数据表名称
     * @return 数据总条目
     */
    public static int getDataCount(SQLiteDatabase db, String tableName){
        int count = 0;
        if (db != null){
            Cursor cursor = db.rawQuery("select * from " + tableName, null);
            // 获取cursor中的数据总数
            count = cursor.getCount();
        }
        return count;
    }

    /**
     * 根据当前页码查询获取该页码对应的集合数据
     * @param db 数据库对象
     * @param tableName 数据表名称
     * @param currentPage 当前页码
     * @return 当前页对应的集合
     *
     * select * from city ?,? 如何根据当前页码获取该页数据
     * 0,20 1
     * 20,20 2
     * 40,20 3
     */
    public static List<Fruit> getListByCurrentPage(SQLiteDatabase db, String  tableName, int currentPage, int pageSize){
        int index = (currentPage - 1) * pageSize;
        Cursor cursor = null;
        if (db != null){
            String sql = "select * from " + tableName + " limit ?,?";
            cursor = db.rawQuery(sql, new String[]{ index + "", pageSize + ""});
        }
        return cursorToList(cursor);
    }
}
