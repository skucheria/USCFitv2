
package com.example.team8.uscfit;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());
    }

    @Test
    public void signupActivityTest() {
        onView(withId(R.id.editText)).perform(typeText("test-signup@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button3)).perform(click());
    }
}



