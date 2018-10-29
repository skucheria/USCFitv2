
package com.example.team8.uscfit;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void navbarCaloriesTest() {
        onView(withId(R.id.nav_calories)).perform(click());
    }

    @Test
    public void navbarTodoTest() {
        onView(withId(R.id.nav_todo)).perform(click());
    }

    @Test
    public void navbarStepsTest() {
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.nav_steps)).perform(click());
    }




}



