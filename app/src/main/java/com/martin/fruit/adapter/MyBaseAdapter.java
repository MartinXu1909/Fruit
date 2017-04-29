package com.martin.fruit.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.martin.fruit.R;
import com.martin.fruit.bean.Fruit;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Martin on 2017/4/29 0029.
 */

public class MyBaseAdapter extends BaseAdapter {
    public Context mContext;
    private List<Fruit> mFruits;

    public List<Fruit> getFruits() {
        return mFruits;
    }

    public MyBaseAdapter(Context context, List<Fruit> fruits) {
        mContext = context;
        mFruits = fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        mFruits = fruits;
    }

    @Override
    public int getCount() {
        return mFruits.size();
    }

    @Override
    public Object getItem(int position) {
        return mFruits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fruit_list_item, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.icon);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.wei = (TextView) convertView.findViewById(R.id.wei);
            holder.effect = (TextView) convertView.findViewById(R.id.effect);
            holder.taboo = (TextView) convertView.findViewById(R.id.taboo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        File file = new File(Environment.getExternalStorageDirectory() + File.separator +  "image", mFruits.get(position).get缩略图());
//        Glide.with(mContext).load(file).into(holder.image);
//        Glide.with(mContext).load(mFruits.get(position)).into(holder.image);

        Bitmap icon = getImageFromAssetsFile(mContext, mFruits.get(position).get缩略图());
        holder.image.setImageBitmap(icon);
        holder.name.setText(mFruits.get(position).get标题());
        holder.wei.setText(mFruits.get(position).get副标题());
        holder.effect.setText(mFruits.get(position).get功效());
        holder.taboo.setText(mFruits.get(position).get禁忌人群());
        return convertView;
    }
    static class ViewHolder{
        ImageView image;
        TextView name, wei, effect, taboo;
    }

    /** 读取Assets文件夹中的图片资源
     * @param context
     * @param fileName 图片名称
     * @return
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
