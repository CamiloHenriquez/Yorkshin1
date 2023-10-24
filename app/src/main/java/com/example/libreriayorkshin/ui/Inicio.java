package com.example.libreriayorkshin.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.libreriayorkshin.R;
import com.example.libreriayorkshin.modelo.Tiendas;

import java.util.ArrayList;


public class Inicio extends Fragment {

    private ArrayList<Tiendas> listado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    public ArrayList<Tiendas> cargar_datos(){
        listado = new ArrayList<>();
        listado.add(new Tiendas("https://firebasestorage.googleapis.com/v0/b/yorkshin-201cc.appspot.com/o/Tiendas%2Ftienda1.jpeg?alt=media&token=ee191bc7-44e4-4fd6-a65d-e56a79d72fd0&_gl=1*zh3m0o*_ga*OTUyNTA4NDc2LjE2OTc2NTg4NzY.*_ga_CW55HF8NVT*MTY5NzY3NjI2Mi42LjEuMTY5NzY3NjI2Ny41NS4wLjA.","MANGAKA STORE"));
        listado.add(new Tiendas("https://firebasestorage.googleapis.com/v0/b/yorkshin-201cc.appspot.com/o/Tiendas%2Ftienda2.jpeg?alt=media&token=007202ac-947c-48fc-bc52-29b1acab46a7&_gl=1*1xzhqfv*_ga*OTUyNTA4NDc2LjE2OTc2NTg4NzY.*_ga_CW55HF8NVT*MTY5NzY3NjI2Mi42LjEuMTY5NzY3NjM5OS42MC4wLjA.","Pequeno Tokyo"));
        listado.add(new Tiendas("https://firebasestorage.googleapis.com/v0/b/yorkshin-201cc.appspot.com/o/Tiendas%2Ftienda3.jpeg?alt=media&token=8c80707b-c200-4fda-b549-dcebe35cef8b&_gl=1*1qv61rz*_ga*OTUyNTA4NDc2LjE2OTc2NTg4NzY.*_ga_CW55HF8NVT*MTY5NzY3NjI2Mi42LjEuMTY5NzY3NjQ4NS42MC4wLjA.","GINSAMA"));
        listado.add(new Tiendas("https://firebasestorage.googleapis.com/v0/b/yorkshin-201cc.appspot.com/o/Tiendas%2Ftienda4.jpeg?alt=media&token=7502edd4-2d9b-4f31-b66d-190487cf0f42&_gl=1*1thz5sv*_ga*OTUyNTA4NDc2LjE2OTc2NTg4NzY.*_ga_CW55HF8NVT*MTY5NzY3NjI2Mi42LjEuMTY5NzY3NjUyMi4yMy4wLjA.","Yuki's"));
        return listado;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView l = (ListView) view.findViewById(R.id.lvTiendas);
        Adaptador adaptador = new Adaptador(getContext(),cargar_datos());
        l.setAdapter(adaptador);
    }
}