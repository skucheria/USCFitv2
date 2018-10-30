package com.example.team8.uscfit;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalorieCounterUnitTest extends Calories_Fragment{
    private Calories_Fragment testFragment = new Calories_Fragment();

    @Test
    public void BMICalculator_isCorrect() {
        double height = 1.6;
        double weight = 65;
        double bmi = testFragment.BMICalculator(height, weight);
        assertEquals(25.390625, bmi, 0.1);
    }

    @Test
    public void calculateCalories_isCorrect() {
        double valueFromHash = 8;
        double timeCompleteDouble = 2;
        double calories = testFragment.calculateCalories(valueFromHash, timeCompleteDouble);
        assertEquals(16, calories, 0.1);
    }

    @Test
    public void populateHashMap_isCorrect() {
        testFragment.populateHashMap();
        assertEquals(8, testFragment.activityToCaloriesBurned.get("Basketball"), 0.1);
    }

    @Test
    public void setUpString_isCorrect() {
        String bmiToPrintString = "20";
        String caloriesToPrintString = "32";
        assertEquals("BMI: " + bmiToPrintString + "\n" + "Calories Burned: " +
                caloriesToPrintString, "BMI: " + "20" + "\n" + "Calories Burned: " +
                "32");
    }
}
