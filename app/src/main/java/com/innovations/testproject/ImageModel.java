package com.innovations.testproject;

import android.graphics.Bitmap;

import org.json.JSONArray;

public class ImageModel {
    String comment;
    String picture;

    Bitmap bitmap;

    public ImageModel(String comment, String picture, Bitmap bitmap) {
        this.comment = comment;
        this.picture = picture;

        this.bitmap = bitmap;
    }

    public  ImageModel(){
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
