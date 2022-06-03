package com.example.carpoolbuddy;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class VehicleRecyclerViewAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    private vehicleListener listener;

    ArrayList<String> carOwnerNames;
    ArrayList<String> carSeatsLeft;

    public VehicleRecyclerViewAdapter(ArrayList ownerData, ArrayList seatsData, vehicleListener listener) {
        carOwnerNames = ownerData;
        carSeatsLeft = seatsData;
        this.listener = listener;

    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.nameText.setText(carOwnerNames.get(position));
        holder.seatsLeftTextView.setText(carSeatsLeft.get(position) + "seats left");
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cis_row_view, parent, false);
        VehicleViewHolder holder = new VehicleViewHolder(myView, listener);
        return holder;
    }

    public void addNewdata (ArrayList ownerData, ArrayList seatsData){
        carOwnerNames = ownerData;
        carSeatsLeft = seatsData;
    }

    @Override
    public int getItemCount() {
        return carOwnerNames.size();
    }

    public interface vehicleListener{
        void vehicleOnClick(int position);
    }

}
