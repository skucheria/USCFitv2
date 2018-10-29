package com.example.team8.uscfit.objects;

public class TodoItem {
    private String uid;
    private String desc;
    private String dateTime;

    public TodoItem() {
        this.uid = "";
        this.desc = "";
        this.dateTime = "";
    };

    public TodoItem(String uid, String desc, String dateTime) {
        this.uid = uid;
        this.desc = desc;
        this.dateTime = dateTime;
    }

    public String getUid() { return uid; }
    public String getDesc() {return desc; }
    public String getDateTime() { return dateTime; }

    public void setUid(String uid) { this.uid = uid; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
}
