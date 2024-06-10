package com.example.fe.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fe.R;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    private List<Medicine> medicineList;

    public MedicineAdapter(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_item, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);
        holder.medicineName.setText(medicine.getName());
        holder.medicinePrice.setText(String.valueOf(medicine.getPrice()));
        // Set image resource and other data here if necessary

        holder.addToCartButton.setOnClickListener(v -> {
            // Implement add to cart logic here
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    static class MedicineViewHolder extends RecyclerView.ViewHolder {
        ImageView medicineImage;
        TextView medicineName;
        TextView medicinePrice;
        Button addToCartButton;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineImage = itemView.findViewById(R.id.obat_image);
            medicineName = itemView.findViewById(R.id.obat_name);
            medicinePrice = itemView.findViewById(R.id.obat_price);
            addToCartButton = itemView.findViewById(R.id.add_to_cart_button);
        }
    }
}

