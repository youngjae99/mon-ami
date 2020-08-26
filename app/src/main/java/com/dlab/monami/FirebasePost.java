package com.dlab.monami;

import java.util.HashMap;
import java.util.Map;

public class FirebasePost {
    public String title; //수정
    public String symptom;
    public String comment;

    public FirebasePost(){

    }

    public FirebasePost(String title, String symptom, String comment){ //수정
        this.title=title;
        this.symptom=symptom;
        this.comment=comment;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result=new HashMap<>();
        result.put("title",title);
        result.put("symptom",symptom);
        result.put("comment",comment);
        return result;
    }
}
