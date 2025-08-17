package com.example.ass_restapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ass_restapi.R;
import com.example.ass_restapi.models.Fruit;

import java.util.ArrayList;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {

    private Context context;
    private ArrayList<Fruit> fruits;

    public FruitAdapter(Context context, ArrayList<Fruit> fruits) {
        this.context = context;
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fruit, parent, false);
        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder holder, int position) {
        Fruit fruit = fruits.get(position);
        holder.tvOrigin.setText(fruit.getOrigin());
        holder.tvPrice.setText("Giá: " + fruit.getPrice() + "đ");
        holder.tvQuantity.setText("Số lượng: " + fruit.getQuantity());

        Glide.with(context)
                .load(fruit.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgFruit);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public static class FruitViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrigin, tvPrice, tvQuantity;
        ImageView imgFruit;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrigin = itemView.findViewById(R.id.tvOrigin);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            imgFruit = itemView.findViewById(R.id.imgFruit);
        }
    }
}