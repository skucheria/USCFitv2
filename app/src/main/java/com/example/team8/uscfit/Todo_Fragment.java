package com.example.team8.uscfit;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.view.*;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import java.util.Calendar;



public class Todo_Fragment extends Fragment {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.todo_layout, container, false);
        Button button = view.findViewById(R.id.btnAddItem);
        Button btnDatePicker = view.findViewById(R.id.btn_date);
        Button btnTimePicker = view.findViewById(R.id.btn_time);
        final EditText txtDate = view.findViewById(R.id.in_date);
        final EditText txtTime = view.findViewById(R.id.in_time);

//        btnDatePicker=getView().findViewById(R.id.btn_date);
//        btnTimePicker=getView().findViewById(R.id.btn_time);
//        txtDate=getView().findViewById(R.id.in_date);
//        txtTime=getView().findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnTimePicker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                EditText etNewItem = getView().findViewById(R.id.etNewItem);
                String itemText = etNewItem.getText().toString();
                EditText etInDate = getView().findViewById(R.id.in_date);
                String dateText = etInDate.getText().toString();
                EditText etInTime = getView().findViewById(R.id.in_time);
                String timeText = etInTime.getText().toString();
                String allText = itemText + " REMINDER AT: " + dateText + " " + timeText;
                if(!itemText.equals("")) { //add if not empty
                    itemsAdapter.add(allText);
                    etNewItem.setText("");
                    txtDate.getText().clear();
                    txtTime.getText().clear();
                }
            }
        });
        return view;
    }

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lvItems = getView().findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Activity");
        items.add("Second Activity");
        setupListViewListener();

    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new ListView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }

                });
    }

//    @Override
//    public void onClick(View v) {
//
//        if (v == btnDatePicker) {
//
//            // Get Current Date
//            final Calendar c = Calendar.getInstance();
//            mYear = c.get(Calendar.YEAR);
//            mMonth = c.get(Calendar.MONTH);
//            mDay = c.get(Calendar.DAY_OF_MONTH);
//
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
//                    new DatePickerDialog.OnDateSetListener() {
//
//                        @Override
//                        public void onDateSet(DatePicker view, int year,
//                                              int monthOfYear, int dayOfMonth) {
//
//                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                        }
//                    }, mYear, mMonth, mDay);
//            datePickerDialog.show();
//        }
//        if (v == btnTimePicker) {
//
//            // Get Current Time
//            final Calendar c = Calendar.getInstance();
//            mHour = c.get(Calendar.HOUR_OF_DAY);
//            mMinute = c.get(Calendar.MINUTE);
//
//            // Launch Time Picker Dialog
//            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
//                    new TimePickerDialog.OnTimeSetListener() {
//
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay,
//                                              int minute) {
//
//                            txtTime.setText(hourOfDay + ":" + minute);
//                        }
//                    }, mHour, mMinute, false);
//            timePickerDialog.show();
//        }
//    }
}
