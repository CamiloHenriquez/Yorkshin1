package com.example.libreriayorkshin.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.libreriayorkshin.R;
import com.example.libreriayorkshin.modelo.Tiendas;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter{

    private Context contexto;
    private ArrayList<Tiendas> listItems;

    public Adaptador(Context contexto, ArrayList<Tiendas> listItems) {
        this.contexto = contexto;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(contexto).inflate(R.layout.items_lista_tiendas,null);
        ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        TextView tvNombreTienda = (TextView) view.findViewById(R.id.tvNombreTienda);
        Tiendas t = (Tiendas) getItem(i);
        Glide.with(contexto)
                .load(t.getRuta())
                .centerCrop()
                .fitCenter()
                .into(ivFoto);
        ivFoto.getLayoutParams().height = 650;
        ivFoto.getLayoutParams().width = 650;
        tvNombreTienda.setText(t.getNombre());
        return view;
    }
}
