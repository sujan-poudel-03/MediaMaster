package com.example.mediamaster.Fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mediamaster.R;

import java.util.ArrayList;

public class Audio extends Fragment {

    private ListView listView;
    private ArrayList<String> audioList;

//    public AudioFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio, container, false);

        listView = view.findViewById(R.id.audio_listview);
        audioList = new ArrayList<>();
        getAudioList();

        if(audioList.size() == 0) {
            audioList.add("No audio files found");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, audioList);
        listView.setAdapter(adapter);

        return view;
    }

    private void getAudioList() {
        String[] projection = { MediaStore.Audio.Media.TITLE };
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                sortOrder);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    audioList.add(cursor.getString(0));
                }
            } else {
//                audioList.add("No audio files found");
            }
            cursor.close();
        }
    }
}