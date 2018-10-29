package com.example.team8.uscfit;

import com.example.team8.uscfit.pedometer.StepFilter;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class StepFilterUnitTest extends StepFilter {
    @Test
    public void sum_isCorrect() {
        float[] arr = new float[3];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;

        assertEquals(6, sum(arr), 0.1);
    }

    @Test
    public void norm_isCorrect() {
        float[] arr = new float[3];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;

        assertEquals(3.74, norm(arr), 0.1);
    }

    @Test
    public void dot_isCorrect() {
        float[] arr1 = new float[3];
        arr1[0] = 1;
        arr1[1] = 2;
        arr1[2] = 3;

        float[] arr2 = new float[3];
        arr2[0] = 1;
        arr2[1] = 2;
        arr2[2] = 3;

        assertEquals(14, dot(arr1, arr2), 0.1);
    }
}
