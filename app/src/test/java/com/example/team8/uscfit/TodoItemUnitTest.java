package com.example.team8.uscfit;

import com.example.team8.uscfit.objects.TodoItem;
import com.example.team8.uscfit.objects.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class TodoItemUnitTest extends TodoItem {

    String uid = "lkajlkjsdfjlkdjf";
    String desc = "Basketball Practice";
    String date = "10-02-2018";

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
    public void getDate_isCorrect() {
        ti.setDateTime(date);
        assertEquals("10-02-2018", ti.getDateTime());
    }

}
