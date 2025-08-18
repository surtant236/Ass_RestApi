package com.example.ass_restapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_restapi.R;
import com.example.ass_restapi.models.Fruit;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ManageViewHolder> {

    private Context context;
    private ArrayList<Fruit> products;
    private OnActionListener listener;

    // callback để xử lý Edit / Delete
    public interface OnActionListener {
        void onEdit(Fruit fruit);
        void onDelete(Fruit fruit);
    }

    public ProductAdapter(Context context, ArrayList<Fruit> products, OnActionListener listener) {
        this.context = context;
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fruit_product, parent, false);
        return new ManageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageViewHolder holder, int position) {
        Fruit product = products.get(position);

        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice() + " đ");
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(product);
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(product);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ManageViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQuantity;
        ImageButton btnEdit, btnDelete;

        public ManageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
