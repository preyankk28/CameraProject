package com.innovations.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ImageDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Uri uri;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        bitmap = getIntent().getParcelableExtra("bitmap");
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text);
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);

        }else{
            uri =   Uri.parse(getIntent().getStringExtra("image"));
            Glide.with(imageView.getContext())
                    .load(uri)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView);
        }

        textView.setText(getIntent().getStringExtra("comment"));


    }
}