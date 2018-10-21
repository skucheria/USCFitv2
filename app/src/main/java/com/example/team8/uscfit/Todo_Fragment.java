package com.example.team8.uscfit;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.util.Log;
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



public class Todo_Fragment extends Fragment {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.todo_layout, container, false);
        Button button = view.findViewById(R.id.btnAddItem);
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                EditText etNewItem = getView().findViewById(R.id.etNewItem);
                String itemText = etNewItem.getText().toString();
                if(!itemText.equals("")) { //add if not empty
                    itemsAdapter.add(itemText);
                    etNewItem.setText("");
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


}
