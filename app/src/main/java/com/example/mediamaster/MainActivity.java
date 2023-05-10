package com.example.mediamaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mediamaster.Fragment.Audio;
import com.example.mediamaster.Fragment.Image;
import com.example.mediamaster.Fragment.Video;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button image_btn =(Button) findViewById(R.id.image);
        Button audio_btn = (Button) findViewById(R.id.audio);
        Button video_btn = (Button) findViewById(R.id.video);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragement(new Image());
                image_btn.setBackgroundColor(getColor(R.color.white));
                image_btn.setTextColor(getColor(R.color.black));
            }
        });

        audio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragement(new Audio());
                audio_btn.setBackgroundColor(getColor(R.color.white));
                audio_btn.setTextColor(getColor(R.color.black));
            }
        });

        video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragement(new Video());
                video_btn.setBackgroundColor(getColor(R.color.white));
                video_btn.setTextColor(getColor(R.color.black));
            }
        });
    };


    private void replaceFragement(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_section,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.listview_icon:
                ActionMenuItemView listview_btn = (ActionMenuItemView) findViewById(R.id.listview_icon);
                listview_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, ListViewExample.class);
                        startActivity(intent);
                    }
                });
            case R.id.gridview_icon:
                ActionMenuItemView gridview_btn = (ActionMenuItemView) findViewById(R.id.gridview_icon);
                gridview_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, GridViewExample.class);
                        startActivity(intent);
                    }
                });
            case R.id.recyclerview_icon:
                ActionMenuItemView recycler_btn = (ActionMenuItemView) findViewById(R.id.recyclerview_icon);
                recycler_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, RecyclerViewExample.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Exit App");
        builder.setMessage("Do you want to exit App");
        builder.setCancelable(true);
        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }
        );
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );
        AlertDialog alert = builder.create();
        alert.show();
    }
}