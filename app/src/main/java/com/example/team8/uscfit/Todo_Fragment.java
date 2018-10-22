package com.example.team8.uscfit;

import android.content.Context;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;


public class Todo_Fragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {
        System.out.println("IN THE MOTHERFUCKIG ONCREATE FOR TODOVIEW AHHHHHHHHHHHHHH");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.todo_layout, container, true);
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                System.out.println("TODO LIST HAS A PARENT VIEW");
                parent.removeAllViews();
            }
        }
    }
}
