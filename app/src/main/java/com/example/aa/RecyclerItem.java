package com.example.aa;

public class RecyclerItem {
    private String titleStr ;
    private String descStr ;

    RecyclerItem(String title, String desc){
        titleStr = title ;
        descStr = desc ;
    }

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}
