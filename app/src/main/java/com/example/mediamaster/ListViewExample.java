package com.example.mediamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = (ListView) findViewById(R.id.listview_item);
//        TextView textView = (TextView) findViewById(R.id.text_view);

//        String[] listItem = getResources().getStringArray(R.array.array_foodItems);
        String food_listItem[] = {"Fulki", "Pani Puri", "Chawmin", "PIZZA", "MOMO", "Samosa", "Pakauda"};
        int food_listImage[] = {R.drawable.fulki, R.drawable.pani_puri, R.drawable.chawmin, R.drawable.pizza, R.drawable.momo, R.drawable.samosa, R.drawable.pakauda};
        int food_price[] = {60, 50, 100, 160, 120, 30, 20 };

        /* Custom ListViewAdapter Class */
        CustomListViewAdapter adapter;
        adapter = new CustomListViewAdapter(this,food_listItem, food_listImage, food_price);
        listView.setAdapter(adapter);


//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.listview_item, R.id.text_view ,listItem);
//        listView.setAdapter(adapter);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);

        //Default Listview
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, food_listItem);
//        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.update:
                // Your Stuff

                Toast.makeText(this, "Update is clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                //Your Stuff
                Toast.makeText(this, "Delete is clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}