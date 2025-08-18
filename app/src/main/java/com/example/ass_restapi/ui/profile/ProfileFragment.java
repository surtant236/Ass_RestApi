package com.example.ass_restapi.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.ass_restapi.R;
import com.example.ass_restapi.ui.product.ProductManagementActivity;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // ánh xạ nút từ XML
        Button btnManageProducts = view.findViewById(R.id.btnManageProducts);

        // sự kiện mở màn ProductManagementActivity
        btnManageProducts.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProductManagementActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
