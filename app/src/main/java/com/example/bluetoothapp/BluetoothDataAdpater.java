package com.example.bluetoothapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BluetoothDataAdpater extends RecyclerView.Adapter<BluetoothDataAdpater.BlutoothDataViewHolder> {
    private final ArrayList<BluetoothDataInstance> bluetoothData;

    public BluetoothDataAdpater(ArrayList<BluetoothDataInstance> bluetoothData) {
        this.bluetoothData = bluetoothData;
    }

    public class BlutoothDataViewHolder extends RecyclerView.ViewHolder {
        public TextView bluetoothTextView;

        public BlutoothDataViewHolder(@NonNull View itemView) {
            super(itemView);
            bluetoothTextView = itemView.findViewById(R.id.bluetooth_card_name);
        }
    }

    @NonNull
    @Override
    public BlutoothDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bluetooth_card,parent,false);
        BlutoothDataViewHolder blutoothDataViewHolder = new BlutoothDataViewHolder(view);
        return blutoothDataViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull BlutoothDataViewHolder holder, int position) {
        BluetoothDataInstance currentItem = bluetoothData.get(position);
        holder.bluetoothTextView.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return bluetoothData.size();
    }


}
