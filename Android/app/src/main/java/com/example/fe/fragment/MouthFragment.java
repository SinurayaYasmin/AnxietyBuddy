package com.example.fe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.example.fe.R;
import com.example.fe.api.ApiService;
import com.example.fe.model.Medicine;
import com.example.fe.model.Adapter;
import com.example.fe.model.MedicineAdapter;

public class MouthFragment extends Fragment {
    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private List<Medicine> medicineList;
    private ApiService apiService;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mouth, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data
        medicineList = new ArrayList<>();
        medicineList.add(new Medicine("Mouth Medicine A", 10000, "SKIN"));
        medicineList.add(new Medicine("Skin Medicine B", 15000, "SKIN"));
        medicineList.add(new Medicine("Skin Medicine C", 2000, "SKIN"));
        medicineList.add(new Medicine("Skin Medicine C", 2000, "SKIN"));
        medicineList.add(new Medicine("Skin Medicine C", 2000, "SKIN"));
        medicineList.add(new Medicine("Skin Medicine C", 2000, "SKIN"));


        // Add more dummy data or fetch from a data source

        adapter = new MedicineAdapter(medicineList);
        recyclerView.setAdapter(adapter);




        return view;
    }
}
