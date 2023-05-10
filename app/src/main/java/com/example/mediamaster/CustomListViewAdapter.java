package com.example.mediamaster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.NonNull;

public class CustomListViewAdapter extends ArrayAdapter<String> {

    Context context;
    String[] foodItem;
    int[] foodItemImage;
    int[] foodItemPrice;

    public CustomListViewAdapter(Context context, String[] foodItem, int[] foodItemImage, int[] foodItemPrice) {
        super(context, R.layout.custom_listview_item, foodItem);
        this.context=context;
        this.foodItem=foodItem;
        this.foodItemImage=foodItemImage;
        this.foodItemPrice=foodItemPrice;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.custom_listview_item, null, true);

        ImageView foodImage = (ImageView) rowView.findViewById(R.id.imageView_foodImage);
        TextView foodName = (TextView) rowView.findViewById(R.id.textView_foodName);
        TextView foodPrice = (TextView) rowView.findViewById(R.id.textView_price);

        foodImage.setImageResource(foodItemImage[i]);
        foodName.setText(foodItem[i]);
        foodPrice.setText(String.valueOf(foodItemPrice[i]));

        return rowView;
    }
}
