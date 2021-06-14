package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button searchButton;
    TextView loadingText;
    RecyclerView recyclerView;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.search_button);
        loadingText = findViewById(R.id.loading_text);
        recyclerView = findViewById(R.id.recycler_view);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        searchButton.setOnClickListener(v -> {
            loadingText.setText("Searching...");
            searchButton.setEnabled(false);
            bluetoothAdapter.startDiscovery();
        });


    }
}