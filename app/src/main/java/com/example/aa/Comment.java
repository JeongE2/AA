package com.example.aa;

public class Comment {
    private String comment;
    private int like;
    private boolean select;
    private String nickname;
public Comment(){
}
    public Comment(String comment, int like, boolean select, String nickname) {
        this.comment=comment;
        this.like=like;
        this.select=false;
        this.nickname=nickname;
    }

    public String getComment(){return comment;}
    public int getLike(){return like;}
    public boolean getSelect(){return select;}
    public void setComment(String comment){this.comment=comment;}
    public void setLike(int like){this.like=like;}
    public void setSelect(boolean select){this.select=select;}
    public String getNickname(){return nickname;}
    public void setNickname(String nickname){this.nickname=nickname;}
}
