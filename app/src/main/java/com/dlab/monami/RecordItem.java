package com.dlab.monami;

import androidx.annotation.NonNull;

class RecordItem {
    private String Time;
    private String Writer;
    //private boolean isSelected;

    public RecordItem(String _Time, String _Writer)
    {
        this.Time = _Time;
        this.Writer = _Writer;
        //this.isSelected = isSelected;
    }


    public void setTime(String time) {
        Time = time;
    }

    public String getTime() {
        return Time;
    }

    public void setWriter(String writer) { Writer = writer; }

    public String getWriter() {
        return "하준맘";
    }

    @NonNull
    @Override
    public String toString() {
        super.toString();
        return "작성일시: " + Time + " 작성자: " + Writer;
    }

    //public boolean isSelected() { return isSelected; }

//    public void setSelected(boolean isSelected) {
//        this.isSelected = isSelected;
//    }

    //    public String getId() { return id;}
}
