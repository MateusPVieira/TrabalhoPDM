package com.edu.trabalhohospital.adapter;

import android.content.Context;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.trabalhohospital.R;
import com.edu.trabalhohospital.activity.AppointmentDetailActivity;
import com.edu.trabalhohospital.entity.Appointment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    private Context mContext;
    private List<Appointment> mAppointmentList;
    DatabaseReference appointmentRef = FirebaseDatabase.getInstance().getReference().child("consultas");

    public AppointmentAdapter(Context context, List<Appointment> appointments) {
        super(context, 0, appointments);
        this.mContext = context;
        mAppointmentList = appointments;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_appointment, null);
        }

        // Configuração das vistas dentro do item da lista
        TextView namePatientTextView = view.findViewById(R.id.namePatientTextView);
        TextView nameDoctorTextView = view.findViewById(R.id.nameDoctorTextView);
        TextView dayTextView = view.findViewById(R.id.dayTextView);
        ImageView menuButton = view.findViewById(R.id.menuButton);

        // Definindo a posição do item como a "tag" do botão de menu
        menuButton.setTag(position);
        // Configurando o clique no botão de menu
        menuButton.setOnClickListener(this::showContextMenu);

        // Obtendo o exercício na posição atual
        Appointment appointment = mAppointmentList.get(position);

        // Preenchendo as vistas com os dados do exercício
        namePatientTextView.setText(appointment.getPatientName());
        nameDoctorTextView.setText(appointment.getDoctorName());
        dayTextView.setText(appointment.getDate());


        return view;
    }

    // Método para exibir o menu de contexto (popup)
    private void showContextMenu(View view) {
        // Criando um menu de contexto (popup)
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.context_menu, popupMenu.getMenu());

        // Obtendo a posição do item da lista associada ao botão de menu
        int position = (int) view.getTag();
        // Obtendo o exercício na posição atual
        Appointment appointment = mAppointmentList.get(position);

        // Configurando a lógica de clique nos itens do menu de contexto
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.menu_ver_detalhes) {
                // Lógica para "Ver Detalhes"
                Intent intent = new Intent(mContext, AppointmentDetailActivity.class);
                intent.putExtra("consulta", appointment);
                mContext.startActivity(intent);
                return true;
            } else if (itemId == R.id.menu_excluir) {
                // Lógica para "Excluir"
                // Removendo o exercício da lista e do Firebase Database
                mAppointmentList.remove(position);
                appointmentRef.child(appointment.getId()).removeValue();
                // Notificando o adaptador sobre as mudanças nos dados
                notifyDataSetChanged();
                Toast.makeText(mContext, "Excluir: " + appointment.getPatientName(), Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        });

        // Exibindo o menu de contexto (popup)
        popupMenu.show();
    }
}
