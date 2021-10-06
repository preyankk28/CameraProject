package com.innovations.testproject;

public class ImageModelSub {

    String comment;
    String picture;

    public  ImageModelSub(){
    }

    public ImageModelSub(String comment, String picture) {
        this.comment = comment;
        this.picture = picture;
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
