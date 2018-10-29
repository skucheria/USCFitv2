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
}
