package com.edu.trabalhohospital.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.trabalhohospital.R;
import com.edu.trabalhohospital.adapter.AppointmentAdapter;
import com.edu.trabalhohospital.entity.Appointment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ImageView btnVoltar;
    Button btnNew;
    ListView listView;
    DatabaseReference DB;
    AppointmentAdapter adapter;

    private List<Appointment> appointments = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DB = FirebaseDatabase.getInstance().getReference().child("consultas");

        btnVoltar = (ImageView) findViewById(R.id.btnVoltar2);
        btnNew = (Button) findViewById(R.id.btnNew);
        listView = (ListView) findViewById(R.id.listView);

        adapter = new AppointmentAdapter(this, appointments);
        listView.setAdapter(adapter);

        DB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = postSnapshot.getValue(Appointment.class);
                    appointments.add(appointment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showToast("Erro ao carregar os dados");
            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}