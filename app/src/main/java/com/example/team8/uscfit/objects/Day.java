package com.example.team8.uscfit.objects;
import java.util.*;
import java.io.*;

public class Day {

    //member variables
    private int month;
    private int day;
    private int dailySteps;
    private int calories;
    private HashMap<String, Integer> activitiesToTimeSpent;

    //Getters and Setters


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDailySteps() {
        return dailySteps;
    }

    public void setDailySteps(int dailySteps) {
        this.dailySteps = dailySteps;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public HashMap<String, Integer> getActivitiesToTimeSpent() {
        return activitiesToTimeSpent;
    }

    public void setActivitiesToTimeSpent(HashMap<String, Integer> activitiesToTimeSpent) {
        this.activitiesToTimeSpent = activitiesToTimeSpent;
    }



    //other functions
    public void logActivity(String activity, int timeSpent){

    }
}
