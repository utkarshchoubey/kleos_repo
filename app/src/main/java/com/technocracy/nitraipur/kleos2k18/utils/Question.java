package com.technocracy.nitraipur.kleos2k18.utils;

import java.net.URL;

public class Question {
    String title;
    String content;
    URL imageUrl;
    String hint1;
    String hiht2;
    String hint3;
    String hint4;
    public Question(String title, URL imageUrl, String content, String hint1, String hiht2, String hint3, String hint4){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHiht2() {
        return hiht2;
    }

    public void setHiht2(String hiht2) {
        this.hiht2 = hiht2;
    }

    public String getHint3() {
        return hint3;
    }

    public void setHint3(String hint3) {
        this.hint3 = hint3;
    }

    public String getHint4() {
        return hint4;
    }

    public void setHint4(String hint4) {
        this.hint4 = hint4;
    }


}
