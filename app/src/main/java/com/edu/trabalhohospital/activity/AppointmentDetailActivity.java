package com.edu.trabalhohospital.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.trabalhohospital.R;
import com.edu.trabalhohospital.entity.Appointment;

public class AppointmentDetailActivity extends AppCompatActivity {
    ImageView imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_detail);

        // Configurando o botão de voltar
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ao clicar no botão de voltar, cria-se uma Intent para voltar à ListActivity
                Intent intent = new Intent(AppointmentDetailActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        // Obtendo dados passados da ListActivity através da Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("consulta")) {
            // Recuperando o objeto Exercise passado como Serializable na Intent
            Appointment appointment = (Appointment) intent.getSerializableExtra("consulta");

            // Inicializando as TextViews no layout para exibir detalhes do exercício
            TextView namePatientTextView = findViewById(R.id.txtDetailsPacientName);
            TextView nameDoctorTextView = findViewById(R.id.txtDetailsDoctorName);
            TextView especialityTextView = findViewById(R.id.txtDetailsEspeciality);
            TextView dateTextView = findViewById(R.id.txtDetailsDate);
            TextView valueTextView = findViewById(R.id.txtDetailsValue);

            // Preenchendo as TextViews com detalhes do exercício
            namePatientTextView.setText("Nome do Paciente: " + appointment.getPatientName());
            nameDoctorTextView.setText("Nome do Médico: " + appointment.getDoctorName());
            especialityTextView.setText("Especialidade: " + appointment.getSpecialty());
            dateTextView.setText("Data: " + appointment.getDate());
            valueTextView.setText("Valor: " + appointment.getPrice());
        }
    }
}