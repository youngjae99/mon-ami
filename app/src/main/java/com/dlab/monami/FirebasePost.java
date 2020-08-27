package com.dlab.monami;


import java.util.HashMap;
import java.util.Map;

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

    public FirebasePost(){}

    public Map<String, Object> toMap(){
        HashMap<String, Object> result=new HashMap<>();
        result.put("title",title);
        result.put("symptom",symptom);
        result.put("img",img);
        result.put("comment",comment);
        result.put("writer",writer);
        result.put("time",time);
        return result;
    }
}
