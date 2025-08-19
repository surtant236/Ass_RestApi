package com.example.ass_restapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ass_restapi.R;
import com.example.ass_restapi.models.Fruit;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<Fruit> cartList;
    private OnCartActionListener listener;

    public interface OnCartActionListener {
        void onIncrease(Fruit fruit);
        void onDecrease(Fruit fruit);
        void onRemove(Fruit fruit);
    }

    public CartAdapter(Context context, ArrayList<Fruit> cartList, OnCartActionListener listener) {
        this.context = context;
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fruit_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Fruit fruit = cartList.get(position);

        holder.tvName.setText(fruit.getName());
        holder.tvPrice.setText(fruit.getPrice() + " đ");
        holder.tvQuantity.setText(String.valueOf(fruit.getQuantity()));

        Glide.with(context)
                .load(fruit.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgFruit);

        holder.btnIncrease.setOnClickListener(v -> listener.onIncrease(fruit));
        holder.btnDecrease.setOnClickListener(v -> listener.onDecrease(fruit));
        holder.btnRemove.setOnClickListener(v -> listener.onRemove(fruit));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFruit;
        TextView tvName, tvPrice, tvQuantity;
        ImageButton btnIncrease, btnDecrease, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFruit = itemView.findViewById(R.id.imgFruit);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}