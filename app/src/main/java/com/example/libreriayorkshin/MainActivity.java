package com.example.libreriayorkshin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

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

        if (nombreusuario.equals("camilo") && contrasena.equals("123")) {
            Intent i = new Intent(this, inicio_cliente.class);
            startActivity(i);
        }else {
            Toast.makeText(this,"Error en las credenciales",Toast.LENGTH_SHORT).show();
        }

        //RadioGroup rgTipo = (RadioGroup) findViewById(R.id.rgtipo);

        //int id = rgTipo.getCheckedRadioButtonId();
        //String tipo = "";
        //switch (id){
            //case R.id.rbcliente:
                //tipo = "Cliente";
                //break;
            //case R.id.rbtienda:
                //tipo = "Tienda";
                //break;
            //default:
                //Toast.makeText(this,"Error en el tipo de usuario",Toast.LENGTH_SHORT).show();

        //}
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