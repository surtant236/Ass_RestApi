package com.example.ass_restapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private OnAddToCartClickListener addToCartListener;

    // Interface cho callback thêm vào giỏ hàng
    public interface OnAddToCartClickListener {
        void onAddToCart(Fruit fruit);
    }

    public FruitAdapter(Context context, ArrayList<Fruit> fruits, OnAddToCartClickListener listener) {
        this.context = context;
        this.fruits = fruits;
        this.addToCartListener = listener;
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

        // set dữ liệu
        holder.tvName.setText(fruit.getName());
        holder.tvOrigin.setText(fruit.getOrigin());
        holder.tvPrice.setText("Giá: " + fruit.getPrice() + "đ");

        Glide.with(context)
                .load(fruit.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgFruit);

        // Xử lý nút thêm vào giỏ hàng
        holder.btnAddToCart.setOnClickListener(v -> {
            if (addToCartListener != null) {
                addToCartListener.onAddToCart(fruit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public static class FruitViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvOrigin, tvPrice;
        ImageView imgFruit;
        Button btnAddToCart;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvOrigin = itemView.findViewById(R.id.tvOrigin);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgFruit = itemView.findViewById(R.id.imgFruit);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}