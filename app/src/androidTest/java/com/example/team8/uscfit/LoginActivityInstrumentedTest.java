
package com.example.team8.uscfit;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    // login
    public void loginActivityTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());
    }

    @Test
    // signup
    public void signupActivityTest() {
        Random r = new Random();
        int signupInt = r.nextInt(100000);
        onView(withId(R.id.editText)).perform(typeText("test-signup" + Integer.toString(signupInt) + "@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button3)).perform(click());
    }


    @Test
    // login and add item
    public void todoAddTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));


        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());
    }

    @Test
    // login, add item, remove
    public void todoAddRemoveTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));


        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());

        //remove
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());

    }

    @Test
    // login, fill out form, go to steps, add item
    public void todoAddGotoStepsTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));


        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        //leave and return
        onView(withId(R.id.nav_steps)).perform(click());
        onView(withId(R.id.nav_todo)).perform(click());


        //submit
        onView(withId(R.id.btnAddItem)).perform(click());
    }

    @Test
    // login, fill out form, go to calories, add item
    public void todoAddGotoCaloriesTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));


        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        //leave and return
        onView(withId(R.id.nav_calories)).perform(click());
        onView(withId(R.id.nav_todo)).perform(click());


        //submit
        onView(withId(R.id.btnAddItem)).perform(click());
    }

    @Test
    // login, fill out form, go to calories,submit form, go back, add item
    public void todoAddGotoCaloriesSubmitTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));


        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        //switch to calories
        onView(withId(R.id.nav_calories)).perform(click());

        onView(withId(R.id.heightMeters)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.weightKilograms)).perform(typeText("150"));
        onView(withId(R.id.genderID)).perform(typeText("Female"));
        Espresso.pressBack();
        onView(withId(R.id.timeCompleteID)).perform(typeText("15"));
        Espresso.pressBack();
        onView(withId(R.id.button)).perform(click());

        //return
        onView(withId(R.id.nav_todo)).perform(click());


        //submit
        onView(withId(R.id.btnAddItem)).perform(click());
    }

    @Test
    // signup, fill out form, go to calories,submit form, go back, add item
    public void signupAddGotoCaloriesSubmitTest() {
        Random r = new Random();
        int signupInt = r.nextInt(100000);
        onView(withId(R.id.editText)).perform(typeText("test-signup" + Integer.toString(signupInt) + "@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button3)).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_steps)).perform(click());
        onView(withId(R.id.nav_todo)).perform(click());
        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));


        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        //switch to calories
        onView(withId(R.id.nav_calories)).perform(click());

        onView(withId(R.id.heightMeters)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.weightKilograms)).perform(typeText("150"));
        onView(withId(R.id.genderID)).perform(typeText("Female"));
        Espresso.pressBack();
        onView(withId(R.id.timeCompleteID)).perform(typeText("15"));
        Espresso.pressBack();
        onView(withId(R.id.button)).perform(click());

        //return
        onView(withId(R.id.nav_todo)).perform(click());


        //submit
        onView(withId(R.id.btnAddItem)).perform(click());
    }


    @Test
    // login, add 2 items, removeFirst
    public void todoAdd2RemoveFirstTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());

        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));
        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());

        onView(withId(R.id.etNewItem)).perform(replaceText("Go for a run"));
        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());

        //remove first
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(0).perform(longClick());
    }

    @Test
    // login, add 2 items, removeSecond
    public void todoAdd2RemoveSecondTest() {
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_todo)).perform(click());

        onView(withId(R.id.etNewItem)).perform(replaceText("Lift weights"));
        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());

        onView(withId(R.id.etNewItem)).perform(replaceText("Go for a run"));
        onView(withId(R.id.btn_date)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btn_time)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnAddItem)).perform(click());

        //remove first
        onData(anything()).inAdapterView(withId(R.id.lvItems)).atPosition(1).perform(longClick());
    }


    @Test
    // calorie counter male
    public void calorieCountMale(){
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.nav_calories)).perform(click());

        onView(withId(R.id.heightMeters)).perform(typeText("1"));
        onView(withId(R.id.weightKilograms)).perform(typeText("150"));
        onView(withId(R.id.genderID)).perform(typeText("Female"));
        Espresso.pressBack();
        onView(withId(R.id.timeCompleteID)).perform(typeText("15"));
        Espresso.pressBack();
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    // calorie counter female
    public void calorieCountFemale(){
        onView(withId(R.id.editText)).perform(typeText("email@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("password"));
        Espresso.pressBack();
        onView(withId(R.id.button2)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.nav_calories)).perform(click());

        onView(withId(R.id.heightMeters)).perform(typeText("1"));
        onView(withId(R.id.weightKilograms)).perform(typeText("150"));
        onView(withId(R.id.genderID)).perform(typeText("Male"));
        Espresso.pressBack();
        onView(withId(R.id.timeCompleteID)).perform(typeText("15"));
        Espresso.pressBack();
        onView(withId(R.id.button)).perform(click());
    }


}




