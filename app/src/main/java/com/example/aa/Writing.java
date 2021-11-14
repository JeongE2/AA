package com.example.aa;

public class Writing {
    private String title;
    private  String content;
    private String email;
    private String username;
    private String board;

    public Writing(String title, String content, String username, String board, String email){
        this.title=title;
        this.content=content;
        this.username=username;
        this.board=board;
        this.email=email;
    }

    public Writing(){

    }
    public String getTitle() {return title;}
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public String getBoard(){return board;}
    public String getContent(){return content;}
}
