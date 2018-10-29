package com.example.team8.uscfit;

import com.example.team8.uscfit.objects.TodoItem;
import com.example.team8.uscfit.objects.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class TodoItemUnitTest extends TodoItem {

    String uid = "lkajlkjsdfjlkdjf";
    String desc = "Basketball Practice";
    String endDate = "10-02-2018";
    String endTime = "22:55";

    TodoItem ti = new TodoItem();

    @Test
    public void getUid_isCorrect() {
        ti.setUid(uid);
        assertEquals("lkajlkjsdfjlkdjf", ti.getUid());
    }

    @Test
    public void getDesc_isCorrect() {
        ti.setDesc(desc);
        assertEquals("Basketball Practice", ti.getDesc());
    }

    @Test
    public void getEndDate_isCorrect() {
        ti.setEndDate(endDate);
        assertEquals("10-02-2018", ti.getEndDate());
    }

    @Test
    public void getEndTime_isCorrect() {
        ti.setEndTime(endTime);
        assertEquals("22:55", ti.getEndTime());
    }
}
