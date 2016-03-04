package com.example.dam.proyecto_clienterest.Acciones;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dam.proyecto_clienterest.Pojo.Actividad;
import com.example.dam.proyecto_clienterest.ApiActividades;
import com.example.dam.proyecto_clienterest.R;

import java.util.Calendar;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class EditarActividad extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText etls,etll, etdesc;
    private Spinner spinnerprof,spinnertipo;
    private Button bt,bt2;
    private ApiActividades api;
    private Actividad actividad;
    private String idprof="",fecha;
    private int year,month,day;
    final int DATE_PICKER_ID= 1;
    private TextView fromDateEtxt,toDateEtxt;
    static Context c;
    private boolean semaforo,salida,llegada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        actividad=new Actividad();
        this.c=this;
        Intent i = getIntent();
        actividad=i.getParcelableExtra("actividad");

        spinnerprof=(Spinner) findViewById(R.id.spinnerProf);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.profs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerprof.setAdapter(adapter);

        spinnertipo=(Spinner) findViewById(R.id.spinnerTipo);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertipo.setAdapter(adapter);

        fromDateEtxt=(TextView)findViewById(R.id.eths);
        fromDateEtxt.setText(actividad.getFechai());
        toDateEtxt=(TextView)findViewById(R.id.ethl);
        toDateEtxt.setText(actividad.getFechaf());
        etls=(EditText)findViewById(R.id.etlugars);
        etls.setText(actividad.getLugari());
        etll=(EditText)findViewById(R.id.etlugarl);
        etll.setText(actividad.getLugarf());
        etdesc=(EditText)findViewById(R.id.etDesc);
        etdesc.setText(actividad.getDescripcion());
        bt=(Button)findViewById(R.id.btSalida);
        bt2=(Button)findViewById(R.id.btLlegada);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semaforo=false;
                salida=true;
                showDialog(DATE_PICKER_ID);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semaforo=false;
                llegada=true;
                showDialog(DATE_PICKER_ID);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiActividades.class);

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {}

    public void onNothingSelected(AdapterView<?> parent) {}


    public void guardar(View v){
        String tipo,lugars,lugarl,fechaS,fechaL,descripcion;
        idprof= (1+spinnerprof.getSelectedItemPosition())+"";
        tipo=spinnertipo.getSelectedItem().toString();
        fechaS=fromDateEtxt.getText().toString();
        fechaL=toDateEtxt.getText().toString();
        lugars=etls.getText().toString();
        lugarl=etll.getText().toString();
        descripcion=etdesc.getText().toString();

        actividad.setIdprofesor(idprof);
        actividad.setTipo(tipo);
        actividad.setFechai(fechaS);
        actividad.setFechaf(fechaL);
        actividad.setLugari(lugars);
        actividad.setLugarf(lugarl);
        actividad.setDescripcion(descripcion);


        final Call<Actividad> callActi = api.updateActividad(actividad);
        callActi.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                Log.v("XXXXXXX: " + idprof, "Actividad Subida: " + response.raw());
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.v("XXXXXXX", "No se pudo subir la actividad");
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    int mHour,mMinute;
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;
            fecha=year+"-";
            if((month+1)<10){
                fecha+="0"+(month+1)+"-";
            } else {
                fecha+=(month + 1) + "-";
            }if(day<10){
                fecha+="0"+day;
            } else {
                fecha+=day+"";
            }
            if(semaforo==false) {
                final Calendar ca = Calendar.getInstance();
                mHour = ca.get(Calendar.HOUR_OF_DAY);
                mMinute = ca.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(c,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if(hourOfDay<10){
                                    fecha += " 0" + hourOfDay;
                                } else {
                                    fecha += " " + hourOfDay;
                                }
                                if(minute<10){
                                    fecha +=":0" + minute;
                                } else {
                                    fecha +=":" + minute;
                                }
                                if(salida){
                                    fromDateEtxt.setText(fecha);
                                    salida=false;
                                } else if(llegada){
                                    toDateEtxt.setText(fecha);
                                    llegada=false;
                                }
                            }
                        }, mHour, mMinute, false);
                tpd.show();
                semaforo=true;
            }
        }
    };

    public void cancelar(View v){
        finish();
    }
}
