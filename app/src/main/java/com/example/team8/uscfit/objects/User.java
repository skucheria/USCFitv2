package com.example.team8.uscfit.objects;
import java.util.Calendar;


public class User{
    //member variables
    private String name;
    private int weight;
    private int height;
    private int gender;
    private float bmi;
    private float calories;
    private Calendar calendar;
    private int steps;


    public int getSteps(){
        return this.steps;
    }

    public void setSteps(int step){
        this.steps = step;

    }

//    private ArrayList<Goal> goals;

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public void setCalories(float calories) {this.calories = calories;}

    public float getCalories() {return calories;}

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

//    public ArrayList<Goal> getGoals() {
//        return goals;
//    }
//
//    public void setGoals(ArrayList<Goal> goals) {
//        this.goals = goals;
//    }

    //other functions
    public void calculateBMI(int height, int weight){

    }

//    public void createNewGoal(Goal goal){
//
//    }

    public void logActivity(String activityName, int timeSpent){

    }





}