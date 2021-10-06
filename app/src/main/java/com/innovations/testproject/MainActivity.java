package com.innovations.testproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageRecyclerAdapter imageRecyclerAdapter;
    List<ImageModelSub> list;
    List<ImageModel> list2 = new ArrayList<>();
    int CAMERA_PIC_REQUEST = 2;
    Bitmap bitmap;

    int  TAKE_PICTURE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonkeeper.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface retrofitAPI = retrofit.create(RequestInterface.class);

        Call<List<ImageModelSub>> call2 = retrofitAPI.getImagessuccess();
        call2.enqueue(new Callback<List<ImageModelSub>>() {
            @Override
            public void onResponse(Call<List<ImageModelSub>> call, Response<List<ImageModelSub>> response) {
                Log.e("Response", "Response not null  " + response);
                list = response.body();

                for (int i=0; i<list.size(); i++){
                    ImageModel imageModel = new ImageModel(response.body().get(i).comment,response.body().get(i).picture, bitmap);
                    list2.add(imageModel);

                }

                imageRecyclerAdapter = new ImageRecyclerAdapter(list2, MainActivity.this);
                recyclerView.setAdapter(imageRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<ImageModelSub>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failed ", Toast.LENGTH_LONG).show();
            }
        });



    }

    public void shareclick(View view) {
        dispatchTakePictureIntent(201);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_PIC_REQUEST){
                Dialog dialog = new Dialog(MainActivity.this);
                bitmap = (Bitmap) data.getExtras().get("data");
                View mView = getLayoutInflater().inflate(R.layout.dialog,null);
                ImageView imageView = mView.findViewById(R.id.image);
                imageView.setImageBitmap(bitmap);
                Button button = mView.findViewById(R.id.save);
                EditText editText = mView.findViewById(R.id.et);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageModel imageModel = new ImageModel(editText.getText().toString().trim(),"", bitmap);
                        list2.add(imageModel);
                        imageRecyclerAdapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
                dialog.setContentView(mView);
                dialog.show();
            }


        }
    }


    private void dispatchTakePictureIntent(int requestCode) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA},
                        201);
            }
            return;
        }
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }
}