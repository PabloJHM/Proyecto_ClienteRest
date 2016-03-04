package com.example.dam.proyecto_clienterest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dam.proyecto_clienterest.Acciones.EditarActividad;
import com.example.dam.proyecto_clienterest.Acciones.NuevaActividad;
import com.example.dam.proyecto_clienterest.Pojo.Actividad;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private TextView tv;
    private Adaptador ad;
    private List<Actividad> listaActividades;
    private Retrofit retrofit;
    private ApiActividades api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        listaActividades=new ArrayList<>();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiActividades.class);
        generaLista();
        generaAdaptador();
    }

    public void generaLista(){
        listaActividades=new ArrayList<>();
        Call<List<Actividad>> call = api.getActividades();
        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Response<List<Actividad>> response, Retrofit retrofit) {
                for (Actividad a : response.body()) {
                    listaActividades.add(a);
                }
                generaAdaptador();
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    public void generaAdaptador(){
        ad=new Adaptador(this,R.layout.item_list,listaActividades);
        lv.setAdapter(ad);
        registerForContextMenu(lv);
        ad.notifyDataSetChanged();
    }

    public void nueva(View v){
        Intent i=new Intent(this, NuevaActividad.class);
        startActivityForResult(i, 1);
    }

    public void editarActividad(Actividad a){
        Intent i = new Intent(this,EditarActividad.class);
        i.putExtra("actividad", a);
        startActivityForResult(i,1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo vistainfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int posicion = vistainfo.position;
        switch(item.getItemId()){
            case R.id.mnBorrar:
                borrarActividad(listaActividades.get(posicion));
                return true;
            case R.id.mnEditar:
                editarActividad(listaActividades.get(posicion));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void borrarActividad(final Actividad ac){
        Call<Actividad> call = api.deleteActividad(ac.getId());
        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                Log.v("XXXX", "Borrada: " + response.raw());
                generaLista();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.v("XXXX", "Error: " + t);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                generaLista();
            }
        }
    }
}
