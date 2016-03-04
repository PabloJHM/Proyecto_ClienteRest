package com.example.dam.proyecto_clienterest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dam.proyecto_clienterest.Pojo.Actividad;

import java.util.List;

public class Adaptador extends ArrayAdapter<Actividad> {
    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private List<Actividad> valores;

    static class ViewHolder {
        public TextView tvId,tvprof,tvTipo,tvLugar,tvFH;
    }

    public Adaptador(Context context, int resource, List<Actividad> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.res = resource;
        this.valores = objects;
        this.lInflator = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        for(Actividad a : valores){
            Log.v("La actividad recibida: ",a.toString());
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1
        ViewHolder gv = new ViewHolder();
        if(convertView==null){
            convertView = lInflator.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tvId);
            gv.tvId = tv;
            tv = (TextView) convertView.findViewById(R.id.tvProf);
            gv.tvprof = tv;
            tv = (TextView) convertView.findViewById(R.id.tvTipo);
            gv.tvTipo = tv;
            tv = (TextView) convertView.findViewById(R.id.tvLugar);
            gv.tvLugar = tv;
            tv = (TextView) convertView.findViewById(R.id.tvFechaHora);
            gv.tvFH = tv;
            convertView.setTag(gv);
        } else {
            gv = (ViewHolder) convertView.getTag();
        }
        gv.tvId.setText(valores.get(position).getDescripcion());
        gv.tvprof.setText(getContext().getString(R.string.prof)+" "+valores.get(position).getIdprofesor());
        gv.tvTipo.setText(valores.get(position).getTipo());
        gv.tvLugar.setText(valores.get(position).getLugari()+" - "+valores.get(position).getLugarf());
        gv.tvFH.setText(getContext().getString(R.string.desde)+" "+valores.get(position).getFechai()+" "+getContext().getString(R.string.hasta)+" "+valores.get(position).getFechaf());
        return convertView;
    }
}