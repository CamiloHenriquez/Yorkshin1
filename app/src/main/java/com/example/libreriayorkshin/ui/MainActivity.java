package com.example.libreriayorkshin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.libreriayorkshin.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v) {

        EditText campo1 = this.findViewById(R.id.etnombreusuario);
        String nombreusuario = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.etcontrasena);
        String contrasena = campo2.getText().toString();

        RadioGroup rgTipo = (RadioGroup) findViewById(R.id.rgTipo);

        int id = rgTipo.getCheckedRadioButtonId();
        String tipo ="";
        if (id == R.id.rbCliente) {
            tipo = "cliente";
        } else if (id == R.id.rbTienda) {
            tipo = "tienda";
        } else {
            Toast.makeText(this, "Error en el tipo de usuario", Toast.LENGTH_SHORT).show();
        }


        if (nombreusuario.equals("camilo") && contrasena.equals("123") && tipo.equals("cliente")) {
            Intent i = new Intent(this, inicio_cliente.class);
            startActivity(i);
        }else if (nombreusuario.equals("jose") && contrasena.equals("456") && tipo.equals("tienda")) {
            Intent i = new Intent(this, inicio_tienda.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Error en las credenciales",Toast.LENGTH_SHORT).show();
        }

    }

    public void crearCuenta(View v){

        Intent i = new Intent(this, Crear_usuario.class);
        startActivity(i);
    }

    public void cambiarContrasena(View v){

        Intent i =  new Intent(this, Recuperar_contrasena.class);
        startActivity(i);
    }
}