package com.example.mediamaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private int[] foodImages;
    private String[] foodNames;
    private int[] foodPrices;

    public CustomRecyclerViewAdapter(int[] foodImages, String[] foodNames, int[] foodPrices){
        this.foodImages = foodImages;
        this.foodNames = foodNames;
        this.foodPrices = foodPrices;
    }

    @NonNull
    @Override
    public CustomRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewAdapter.ViewHolder holder, int position) {
        int foodImage = foodImages[position];
        String foodName = foodNames[position];
        int foodPrice = foodPrices[position];

        holder.foodImage.setImageResource(foodImage);
        holder.foodName.setText(foodName);
        holder.foodPrice.setText(String.valueOf(foodPrice));
    }

    @Override
    public int getItemCount() {
        return foodNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.imageView_foodImage);
            foodName = itemView.findViewById(R.id.textView_foodName);
            foodPrice = itemView.findViewById(R.id.textView_price);
        }
    }
}
