package com.example.libreriayorkshin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class inicio_cliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_cliente);

        androidx.appcompat.widget.Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);


        TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                switch (position) {
                    case 0:
                        //llamar al fragmento inicio
                        Inicio i = new Inicio();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2, i).commit();
                        break;
                    case 1:
                        //llamar al fragmento busqueda
                        busqueda b = new busqueda();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2, b).commit();
                        break;

                    case 2:
                        tienda t = new tienda();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2, t).commit();
                        break;

                    case 3:
                        comprar c = new comprar();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2, c).commit();
                        break;

                    case 4:
                        mapa m = new mapa();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor2, m).commit();
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Incorporacion del menu en la activity
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId(); //Recuperar el id del item
        if (id == R.id.op1) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "Seccion cerrada", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}