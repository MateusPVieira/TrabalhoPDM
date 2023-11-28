package com.edu.trabalhohospital.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.edu.trabalhohospital.R;
import com.edu.trabalhohospital.entity.Appointment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddActivity extends AppCompatActivity {
    EditText editTextNomePaciente;
    EditText editTextNomeMedico;
    EditText editTextEspecialidade;
    EditText editTextData;
    EditText editTextValor;
    Button btnCadastrar;
    ImageView btnVoltar;


    private final DatabaseReference DB = FirebaseDatabase.getInstance().getReference();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnCadastrar = (Button) findViewById(R.id.buttonSend);
        editTextNomePaciente = (EditText) findViewById(R.id.editTextNomePaciente);
        editTextNomeMedico = (EditText) findViewById(R.id.editTextNomeMedico);
        editTextEspecialidade = (EditText) findViewById(R.id.editTextEspecialidade);
        editTextData = (EditText) findViewById(R.id.editTextDate);
        editTextValor = (EditText) findViewById(R.id.editTextValor);
        btnVoltar = (ImageView) findViewById(R.id.btnVoltar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarConsulta();
                showToast("Adicionando consulta!");
                Intent intent = new Intent(AddActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private Appointment setConsulta(){
        Appointment appointment = new Appointment();
        appointment.setPatientName(editTextNomePaciente.getText().toString());
        appointment.setDoctorName(editTextNomeMedico.getText().toString());
        appointment.setSpecialty(editTextEspecialidade.getText().toString());
        appointment.setDate(editTextData.getText().toString());
        appointment.setPrice(String.valueOf(editTextValor.getText()));
        return appointment;
    }

    private void adicionarConsulta() {
        Appointment appointment = setConsulta();
        // Gere uma chave única para o treino
        String key = DB.child("consultas").push().getKey();
        appointment.setId(key);
        // Use essa chave para armazenar o treino no Firebase
        DB.child("consultas").child(key).setValue(appointment);

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}