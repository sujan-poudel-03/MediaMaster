package com.example.mediamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class GridViewExample extends AppCompatActivity {

    ArrayList<String> noodels = new ArrayList<>(Arrays.asList("ramen", "waiwai","rumpum", "current","preeti"));
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_example);

        GridView gridView = (GridView) findViewById(R.id.grid_view_item);
//        String noodels[] = {"waiwai","rumpum", "current","preeti"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,noodels);
        gridView.setAdapter(adapter);

        Toast.makeText(this, "Hello to Grid View Example", Toast.LENGTH_SHORT).show();

        registerForContextMenu(gridView);  // used to register item in GridView to perform Context Menu action

        // Popup Menu Implementation
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popupMenu = new PopupMenu(GridViewExample.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.context_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.delete) {
                            // Remove item from data source
                            noodels.remove(i);
                            // Notify adapter of data set change
                            adapter.notifyDataSetChanged();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                popupMenu.show();

                Toast.makeText(getApplicationContext(), "From Popup Menu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Context Menu display from Menu folder
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    // perform action on context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.update:
                Toast.makeText(getApplicationContext(),"Updata clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:

                noodels.remove(position);

                // notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "Delete Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}