package com.example.team8.uscfit.objects;
import java.util.*;
import java.io.*;


public class User{
    //member variables
    private String name;
    private int weight;
    private int height;
    private int gender;
    private double bmi;
    private Calendar calendar;
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

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

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