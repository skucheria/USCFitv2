package com.example.team8.uscfit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.team8.uscfit.objects.TodoItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Todo_Fragment extends Fragment {
    // private Set<Integer> completedPositions = new HashSet<Integer>();
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private ArrayList<TodoItem> initialItems = new ArrayList<TodoItem>();
    private HashMap<Integer,Integer> completed = new HashMap<Integer, Integer>(); //list index to number of long presses

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String uid = user.getUid();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private ArrayList<TodoItem> l;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        items = ((MainActivity) getActivity()).sendToDo();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = inflater.inflate(R.layout.todo_layout, container, false);
        Button button = view.findViewById(R.id.btnAddItem);
        Button btnDatePicker = view.findViewById(R.id.btn_date);
        Button btnTimePicker = view.findViewById(R.id.btn_time);
        final EditText txtDate = view.findViewById(R.id.in_date);
        final EditText txtTime = view.findViewById(R.id.in_time);

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
                                monthOfYear++;
                                String month = String.valueOf(monthOfYear);
                                String day = String.valueOf(dayOfMonth);
                                if (monthOfYear < 10) { month = "0" + month; }
                                if (dayOfMonth < 10){ day = "0" + day; }
//                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                String fullDate = String.valueOf(year) + "-" + month + "-" + day;
                                txtDate.setText(fullDate);
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
                                String hour = String.valueOf(hourOfDay);
                                String min = String.valueOf(minute);
                                if (hourOfDay < 10) { hour = "0" + hour; }
                                if (minute < 10) { min = "0" + min; }
                                txtTime.setText(hour + ":" + min);
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

                TodoItem tdi = new TodoItem(uid, itemText, dateText + " " + timeText);

                if(!itemText.equals("")) { //add if not empty

                    DatabaseReference newRef = mRootRef.child("todoitems").push();
                    newRef.setValue(tdi);

                    Snackbar snackbar = Snackbar
                            .make(view, "REMINDER: "  + itemText, Snackbar.LENGTH_LONG);
                    //Date currentTime = Calendar.getInstance().getTime();
                    //String dateComponents[] = currentTime.toString().split(" ");
                    //Integer hour = Integer.parseInt(dateComponents[3].split(":")[0]);
                    String day = dateText.split("-")[2];

                    Integer hour = Integer.parseInt(timeText.split(":")[0]);
                    if (hour < 17 && day.equals("02"))
                    {
                        snackbar.show();
                    }

                    System.out.println("PHONE TIME: " + hour);
                    System.out.println("DAY: " + day);
                    System.out.println("ACTIVITY TIME: " + timeText);

                    // clear fields
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

        l =  new ArrayList<TodoItem>();
        initialItems = setupList();


        itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
//        lvItems.setAdapter(itemsAdapter);


        lvItems.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
             View row = super.getView(position, convertView, parent);

             completed.put(position,0);

             if(getItem(position).equals("")){
                 row.setBackgroundColor(Color.GREEN);
             }
//             if(getItem(position).equals("Somebody gets killed"))
//             {
//               // do something change color
//               row.setBackgroundColor (Color.RED); // some color
//             }
//             else
//             {
//                 // default state
//               row.setBackgroundColor (Color.WHITE); // default coloe
//             }
               return row;
        }
        });


        setupListViewListener();

    }

    private ArrayList<TodoItem> setupList(){
        Query listQ= mRootRef.child("todoitems").orderByChild("uid").equalTo(uid);
        listQ.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemsAdapter.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    TodoItem item = snap.getValue(TodoItem.class);
                    String allText = item.getDesc() + " REMINDER AT: " + item.getDateTime();
                    itemsAdapter.add(allText);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return l;
    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new ListView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        // Remove the item within array at position
                        completed.put(pos, completed.get(pos) + 1);
                        int k = completed.get(pos);
                        if(k == 1){ //turn light green
                            item.setBackgroundColor(Color.GREEN);
                        }
                        if(k < 7){ //keep light green dont change color

                        }
                        else{ //more than 7, get a "bigger badge" and make gold or some shit
                            item.setBackgroundColor(Color.RED);
                        }
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();

//                        ((MainActivity) getActivity()).updateToDo(items);

                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }

                });
    }


}
