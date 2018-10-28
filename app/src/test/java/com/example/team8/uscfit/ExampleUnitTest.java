package com.example.team8.uscfit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends Calories_Fragment{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void subtraction_isCorrect() {
        assertEquals(0, 2-2);
    }

    private Calories_Fragment testFragment;

    @Test
    public void BMICalculator_isCorrect() {
        double height = 1.6;
        double weight = 65;
        double bmi = testFragment.BMICalculator(height, weight);
        assertEquals(25.390625, bmi, 0.1);
    }
}