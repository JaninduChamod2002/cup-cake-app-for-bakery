package com.example.cakebake;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllCakes extends AppCompatActivity {

    private CakeBakeDB cakeBakeDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cakes);

        // Initialize CakeBakeDB
        CakeBakeDB cakeBakeDB = new CakeBakeDB(this);

        // Get list of cake names
        ArrayList<String> cakeNames = cakeBakeDB.getcupCakeNames();

        // Populate ListView with cake names
        GridView listView = findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cakeNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCake = cakeNames.get(position);
                // Launch new activity to display details
                Intent intent = new Intent(AllCakes.this, CakeDetailsActivity.class);
                intent.putExtra("cakeName", selectedCake);
                startActivity(intent);
            }
        });
    }
}
