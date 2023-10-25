package com.example.libreriayorkshin.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libreriayorkshin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Crear_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Incorporacion del menu en la activity
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId(); //Recuperar el id del item
        if (id == R.id.op1){
            Intent i =  new Intent(this, MainActivity.class);
            startActivity(i);
            Toast.makeText(this,"Seccion cerrada",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void registrarUsuarioEnLaBase(View v){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText usuario = this.findViewById(R.id.usuarioregis);
        String nombreusuario = usuario.getText().toString();

        EditText contra = this.findViewById(R.id.contraregis);
        String contrasena = contra.getText().toString();

        EditText contra2 = this.findViewById(R.id.contraregis2);
        String contrasena2 = contra2.getText().toString();

        if (contrasena2.equals(contrasena)){
            // Create a new user
            Map<String, Object> user = new HashMap<>();
            user.put("usuario", nombreusuario);
            user.put("contrasena",contrasena );

            // Add a new document with a generated ID
            db.collection("usuarios")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error al anadir el documento", e);
                        }
                    });
        }else{
            Toast.makeText(this,"Las contrasenas no son iguales",Toast.LENGTH_SHORT).show();
        }
    }
}