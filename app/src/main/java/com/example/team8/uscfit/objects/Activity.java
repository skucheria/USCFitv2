package com.example.team8.uscfit.objects;
import java.util.*;

public class Activity {

    //member variables
    private HashMap<String, Integer> activityToCalories;
    private String name;

    //getters and setters
    public HashMap<String, Integer> getActivityToCalories() {
        return activityToCalories;
    }

    public void setActivityToCalories(HashMap<String, Integer> activityToCalories) {
        this.activityToCalories = activityToCalories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //other functions
    public int calculateCalories(String activityName, int time){
        return 0;
    }
}
