package com.martin.fruit.readdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.martin.fruit.R;

/**
 * Created by Martin on 2017/4/29 0029.
 */

public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_INTRODUCE = "fruit_introduce";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String intraduce = intent.getStringExtra(FRUIT_INTRODUCE);
//        String imageString = intent.getStringExtra(FRUIT_IMAGE_ID);
//        ImageView fruitImage = (ImageView) findViewById(R.id.image);
//        Bitmap image = MyBaseAdapter.getImageFromAssetsFile(this, imageString);
//        fruitImage.setImageBitmap(image);
        WebView webView = (WebView) findViewById(R.id.web);
        webView.loadDataWithBaseURL(null, intraduce, "text/html", "utf-8",
                null);

    }
}
