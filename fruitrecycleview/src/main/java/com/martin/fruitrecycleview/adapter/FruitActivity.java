package com.martin.fruitrecycleview.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.ImageView;

import com.martin.fruitrecycleview.R;

/**
 * Created by Martin on 2017/5/5 0005.
 */

public class FruitActivity extends AppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    public static final String FRUIT_INTRODUCE = "fruit_introduce";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        String  fruitImageId = intent.getStringExtra(FRUIT_IMAGE_ID);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbars);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        WebView fruitContentText = (WebView) findViewById(R.id.fruit_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(fruitName);
//        Glide.with(this).load(fruitImageId).into(fruitImageView);
        Bitmap icon = MyBaseAdapter.getImageFromAssetsFile(this, fruitImageId);
        fruitImageView.setImageBitmap(icon);
        String fruitContent = intent.getStringExtra(FRUIT_INTRODUCE);
        fruitContentText.loadDataWithBaseURL(null, fruitContent, "text/html", "utf-8",
                null);
    }

}
