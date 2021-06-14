package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button searchButton;
    TextView loadingText;
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    BluetoothAdapter bluetoothAdapter;
    private ArrayList<BluetoothDataInstance> bluetoothData = new ArrayList<>();


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                Log.d("Action","Finished");
                searchButton.setEnabled(true);
                loadingText.setText("");
                layoutManager = new LinearLayoutManager(getApplicationContext());
                adapter = new BluetoothDataAdpater(bluetoothData);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }
            else if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceAddress = device.getAddress();
                Log.d("Action", "Name : " + deviceName + " Address : "+ deviceAddress);
                bluetoothData.add(new BluetoothDataInstance(
                        deviceName == null ? "Unnamed": deviceName)
                );
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.search_button);
        loadingText = findViewById(R.id.loading_text);
        recyclerView = findViewById(R.id.recycler_view);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver,intentFilter);

        searchButton.setOnClickListener(v -> {
            loadingText.setText("Searching...");
            searchButton.setEnabled(false);
            bluetoothAdapter.startDiscovery();
        });




    }
}