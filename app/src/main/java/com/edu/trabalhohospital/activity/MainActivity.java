package com.edu.trabalhohospital.activity;

import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.edu.trabalhohospital.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Adiciona um listener para o clique na toolbar
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    // Cria o menu de overflow na toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Método para iniciar uma nova activity
    private void startActivity(Class<?> cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }

    // Método para exibir o PopupMenu
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.options_menu, popupMenu.getMenu());

        // Adiciona um listener para o clique nos itens do PopupMenu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_adicionar) {
                    // Abre a ListarActivity
                    startActivity(ListActivity.class);
                    return true;
                } else if (itemId == R.id.menu_listar) {
                    // Abre a AdicionarActivity
                    startActivity(AddActivity.class);
                    return true;
                } else {
                    return false;
                }
            }

        });

        // Exibe o PopupMenu
        popupMenu.show();
    }
}
