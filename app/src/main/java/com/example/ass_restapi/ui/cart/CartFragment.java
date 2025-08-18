package com.example.ass_restapi.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.ass_restapi.adapter.CartAdapter;
import com.example.ass_restapi.databinding.FragmentCartBinding;
import com.example.ass_restapi.models.Fruit;
import com.example.ass_restapi.models.Response;
import com.example.ass_restapi.services.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class CartFragment extends Fragment {
    private FragmentCartBinding binding;
    private CartAdapter cartAdapter;
    private List<Fruit> cartList = new ArrayList<>();
    private HttpRequest httpRequest;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        httpRequest = new HttpRequest();

        // Setup RecyclerView với LinearLayoutManager
        binding.rvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(getContext(), (ArrayList<Fruit>) cartList, new CartAdapter.OnCartActionListener() {
            @Override
            public void onIncrease(Fruit fruit) {
                // Xử lý tăng số lượng, ví dụ gọi API hoặc cập nhật local
                Toast.makeText(getContext(), "Tăng số lượng: " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDecrease(Fruit fruit) {
                // Xử lý giảm số lượng, ví dụ gọi API hoặc cập nhật local
                Toast.makeText(getContext(), "Giảm số lượng: " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRemove(Fruit fruit) {
                // Xử lý xóa khỏi giỏ, ví dụ gọi API hoặc cập nhật local
                Toast.makeText(getContext(), "Xóa: " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.rvCart.setAdapter(cartAdapter);

        fetchCartList();

        return root;
    }

    private void fetchCartList() {
        httpRequest.callApi().getListFruits().enqueue(new Callback<Response<ArrayList<Fruit>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<Fruit>>> call, retrofit2.Response<Response<ArrayList<Fruit>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cartList.clear();
                    cartList.addAll(response.body().getData());
                    cartAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Không tải được dữ liệu giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response<ArrayList<Fruit>>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}