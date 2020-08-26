package com.dlab.monami;

public class FirebasePost {
    public Integer type;
    public String title;
    public String symptom;
    public String img;
    public String comment;
    public String time;
    public String writer;



    public FirebasePost(Integer type, String title, String symptom, String img, String comment, String time, String writer){
        this.type=type;
        this.title=title;
        this.symptom=symptom;
        this.img=img;
        this.comment=comment;
        this.time=time;
        this.writer=writer;
    }


}