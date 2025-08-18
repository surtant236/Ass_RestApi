package com.example.ass_restapi.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ass_restapi.R;
import com.example.ass_restapi.adapter.ProductAdapter;
import com.example.ass_restapi.models.Fruit;
import com.example.ass_restapi.models.Response;
import com.example.ass_restapi.services.HttpRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddProduct;   // ✅ dùng FAB thay vì Button
    private ProductAdapter adapter;
    private List<Fruit> productList = new ArrayList<>();
    private HttpRequest httpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        httpRequest = new HttpRequest();

        recyclerView = findViewById(R.id.rvManageProducts);
        fabAddProduct = findViewById(R.id.fabAddProduct); // ✅ ép kiểu đúng FAB

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(this, (ArrayList<Fruit>) productList, new ProductAdapter.OnActionListener() {
            @Override
            public void onEdit(Fruit product) {
                showDialog(product);
            }

            @Override
            public void onDelete(Fruit product) {
                deleteProduct(product.getId());
            }
        });

        recyclerView.setAdapter(adapter);

        fabAddProduct.setOnClickListener(v -> showDialog(null));

        fetchProductList();
    }

    private void fetchProductList() {
        httpRequest.callApi().getListFruits().enqueue(new Callback<Response<ArrayList<Fruit>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<Fruit>>> call, retrofit2.Response<Response<ArrayList<Fruit>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.clear();
                    productList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ProductManagementActivity.this, "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response<ArrayList<Fruit>>> call, Throwable t) {
                Toast.makeText(ProductManagementActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteProduct(String id) {
        httpRequest.callApi().deleteFruits(id).enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, retrofit2.Response<Response<Void>> response) {
                if (response.isSuccessful()) {
                    fetchProductList();
                    Toast.makeText(ProductManagementActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductManagementActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Toast.makeText(ProductManagementActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog(Fruit product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_product, null);
        builder.setView(dialogView);

        EditText etName = dialogView.findViewById(R.id.etName);
        EditText etPrice = dialogView.findViewById(R.id.etPrice);
        EditText etQuantity = dialogView.findViewById(R.id.etQuantity);
        EditText etOrigin = dialogView.findViewById(R.id.etOrigin);
        EditText etImage = dialogView.findViewById(R.id.etImage);
        Button btnSave = dialogView.findViewById(R.id.btnSave);

        if (product != null) {
            etName.setText(product.getName());
            etPrice.setText(String.valueOf(product.getPrice()));
            etQuantity.setText(String.valueOf(product.getQuantity()));
            etOrigin.setText(product.getOrigin());
            etImage.setText(product.getImage());
        }

        AlertDialog dialog = builder.create();
        dialog.show();

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();
            String quantityStr = etQuantity.getText().toString().trim();
            String origin = etOrigin.getText().toString().trim();
            String image = etImage.getText().toString().trim();

            if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(ProductManagementActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            int price = Integer.parseInt(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            if (product == null) {
                // ADD PRODUCT
                Fruit newProduct = new Fruit(null, name, price, quantity, origin, image, null, null);
                httpRequest.callApi().addFruit(newProduct).enqueue(new Callback<Response<Fruit>>() {
                    @Override
                    public void onResponse(Call<Response<Fruit>> call, retrofit2.Response<Response<Fruit>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 200) {
                            fetchProductList();
                            Toast.makeText(ProductManagementActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Fruit>> call, Throwable t) {
                        Toast.makeText(ProductManagementActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                // UPDATE PRODUCT
                Fruit updateProduct = new Fruit(product.getId(), name, price, quantity, origin, image, null, null);
                httpRequest.callApi().updateFruit(product.getId(), updateProduct).enqueue(new Callback<Response<Fruit>>() {
                    @Override
                    public void onResponse(Call<Response<Fruit>> call, retrofit2.Response<Response<Fruit>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getStatus() == 200) {
                            fetchProductList();
                            Toast.makeText(ProductManagementActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(ProductManagementActivity.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Fruit>> call, Throwable t) {
                        Toast.makeText(ProductManagementActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
