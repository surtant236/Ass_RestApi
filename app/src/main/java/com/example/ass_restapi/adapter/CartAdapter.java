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
import com.example.ass_restapi.R;
import com.example.ass_restapi.models.Fruit;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<Fruit> cartItems;
    private OnCartActionListener listener;

    // callback xử lý cộng/trừ/xóa
    public interface OnCartActionListener {
        void onIncrease(Fruit fruit);
        void onDecrease(Fruit fruit);
        void onRemove(Fruit fruit);
    }

    public CartAdapter(Context context, ArrayList<Fruit> cartItems, OnCartActionListener listener) {
        this.context = context;
        this.cartItems = cartItems;
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
        Fruit fruit = cartItems.get(position);

        holder.tvName.setText(fruit.getName());
        holder.tvPrice.setText(fruit.getPrice() + " đ");
        holder.tvQuantity.setText(String.valueOf(fruit.getQuantity()));

        // Hiển thị ảnh - dùng Glide nếu ảnh là URL
        Glide.with(context)
                .load(fruit.getImage()) // Đảm bảo Fruit có phương thức getImageUrl()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgFruit);

        holder.btnIncrease.setOnClickListener(v -> {
            if (listener != null) listener.onIncrease(fruit);
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (listener != null) listener.onDecrease(fruit);
        });

        holder.btnRemove.setOnClickListener(v -> {
            if (listener != null) listener.onRemove(fruit);
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
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