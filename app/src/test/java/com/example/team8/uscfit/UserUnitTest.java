package com.example.team8.uscfit;

import com.example.team8.uscfit.objects.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserUnitTest extends User {

    User u = new User();

    @Test
    public void getBMI_isCorrect(){
        u.setBmi(50);
        assertEquals(50, 50, 0.1);
    }

    @Test
    public void getName_isCorrect(){
        u.setName("Praneeth");
        assertEquals(u.getName(), "Praneeth");
    }

    @Test
    public void getWeight_isCorrect(){
        u.setWeight(30);
        assertEquals(u.getWeight(), 30);
    }

    @Test
    public void getHeight_isCorrect(){
        u.setHeight(40);
        assertEquals(u.getHeight(), 40);
    }

    @Test
    public void getCalories_isCorrect() {
        u.setCalories(100);
        assertEquals(u.getCalories(), 100, 0.1);
    }
}
