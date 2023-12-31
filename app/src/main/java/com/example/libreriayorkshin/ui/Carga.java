package com.example.libreriayorkshin.ui;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libreriayorkshin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Carga#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Carga extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_INTENT = 2;
    private StorageReference mStorage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PackageManager packageManager;
    private Context applicationContext;

    public Carga() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inicio.
     */
    // TODO: Rename and change types and number of parameters
    public static Carga newInstance(String param1, String param2) {
        Carga fragment = new Carga();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    public void tomar_foto(View v){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    //public void cargar_imagen(View v){
        //Permisos p = new Permisos(getApplicationContext());
        //if (p.checkPermissionREAD_EXTERNAL_STORAGE(this)){
            //Intent i = new Intent(Intent.ACTION_PICK);
            //i.setType("image/*");
            //startActivityForResult(i,GALLERY_INTENT);
        //}
    //}


    public void registrarDatos(View v) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //EditText manga = this.findViewById(R.id.nombremanga1);
        //String manga1 = manga.getText().toString();

        //EditText precio = this.findViewById(R.id.preciomanga1);
        //String precio1 = precio.getText().toString();

        //EditText dispo = this.findViewById(R.id.disponibilidad1);
        //String dispo1 = dispo.getText().toString();

        //EditText descripcion = this.findViewById(R.id.descripcion1);
        //String descripcion1 = descripcion.getText().toString();

        //EditText editorial = this.findViewById(R.id.editorial1);
        //String editorial1 = editorial.getText().toString();

        // Create a new user
        Map<String, Object> mangas = new HashMap<>();
        //mangas.put("manga", manga1);
        //mangas.put("precio",precio1 );
        //mangas.put("disponiblidad",dispo1 );
        //mangas.put("descripcion",descripcion1 );
        //mangas.put("editorial",editorial1 );

        // Add a new document with a generated ID
        db.collection("Mangas")
                .add(mangas)
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //Toast.makeText(this, "Imagen sacada con exito", Toast.LENGTH_SHORT).show();
            StorageReference fotoRef = mStorage.child("image/foto1.jpg");
            //Generacion de arreglo de bytes
            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            //Convertir bitmap en formato y calidad deseado
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] data1 = baos.toByteArray();
            //Se sube a firebase la foto
            UploadTask uploadTask = fotoRef.putBytes(data1);
        }else if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Exito al subir la foto", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carga, container, false);
    }

    public PackageManager getPackageManager() {
        return packageManager;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}