package com.example.fe.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fe.R;
import com.example.fe.api.ApiService;
import com.example.fe.model.Medicine;
import com.example.fe.model.MedicineAdapter;

import java.util.ArrayList;
import java.util.List;

public class VitaminFragment extends Fragment {
    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private List<Medicine> medicineList;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vitamin, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewVitamin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data
        medicineList = new ArrayList<>();

        // Initialize data
        medicineList.add(new Medicine("A", 100001, "Vitamin"));
        return view;
    }
}