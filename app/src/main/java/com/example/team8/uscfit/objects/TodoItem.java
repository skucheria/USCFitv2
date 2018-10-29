package com.example.team8.uscfit.objects;

public class TodoItem {
    private String uid;
    private String desc;
    private String endDate;
    private String endTime;

    public TodoItem() {
        this.uid = "";
        this.desc = "";
        this.endDate = "";
        this.endTime = "";
    };

    public TodoItem(String uid, String desc, String endDate, String endTime) {
        this.uid = uid;
        this.desc = desc;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public String getUid() { return uid; }
    public String getDesc() {return desc; }
    public String getEndDate() { return endDate; }
    public String getEndTime() { return endTime; }

    public void setUid(String uid) { this.uid = uid; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
