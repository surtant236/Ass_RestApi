package com.example.ass_restapi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.ass_restapi.adapter.FruitAdapter;
import com.example.ass_restapi.databinding.FragmentHomeBinding;
import com.example.ass_restapi.models.Fruit;
import com.example.ass_restapi.models.Response;
import com.example.ass_restapi.services.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FruitAdapter fruitAdapter;
    private List<Fruit> fruitList = new ArrayList<>();
    private HttpRequest httpRequest;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        httpRequest = new HttpRequest();

        // Setup RecyclerView với GridLayoutManager 2 cột
        binding.recyclerViewFruit.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fruitAdapter = new FruitAdapter(getContext(), (ArrayList<Fruit>) fruitList);
        binding.recyclerViewFruit.setAdapter(fruitAdapter);

        fetchFruitList();

        return root;
    }
    private void fetchFruitList() {
        httpRequest.callApi().getListFruits().enqueue(new Callback<Response<ArrayList<Fruit>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<Fruit>>> call, retrofit2.Response<Response<ArrayList<Fruit>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    fruitList.clear();
                    fruitList.addAll(response.body().getData());
                    fruitAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
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