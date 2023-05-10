package com.example.mediamaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerViewExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        String[] food_listItem = {"Fulki", "Pani Puri", "Chawmin", "PIZZA", "MOMO", "Samosa", "Pakauda"};
        int[] food_listImage = {R.drawable.fulki, R.drawable.pani_puri, R.drawable.chawmin, R.drawable.pizza, R.drawable.momo, R.drawable.samosa, R.drawable.pakauda};
        int[] food_price = {60, 50, 100, 160, 120, 30, 20 };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_example);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Set the layout manager for listview

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Set the layout manager for gridview

        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(food_listImage, food_listItem, food_price);
        recyclerView.setAdapter(adapter);

//        RecyclerViewAdapter adapter = a;

    }
}