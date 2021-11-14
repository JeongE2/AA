package com.example.aa;

import android.graphics.drawable.Drawable;

public class boardItem {
    private Drawable icon;
    private String title;
    private String cont;

    public void setIcon(Drawable icon){
        icon = icon;
    }

    public void setTitle(String title){
        title = title;
    }
    public void setCont(String cont){
        cont=cont;
    }

    public Drawable getIcon(){
        return this.icon;
    }

    public String getTitle(){
        return this.title;

    }

    public String getCont(){
        return this.cont;
    }
}
