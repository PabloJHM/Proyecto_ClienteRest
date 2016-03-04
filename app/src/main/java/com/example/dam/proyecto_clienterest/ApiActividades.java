package com.example.dam.proyecto_clienterest;

import com.example.dam.proyecto_clienterest.Pojo.Actividad;
import com.example.dam.proyecto_clienterest.Pojo.Profesor;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;


public interface ApiActividades {
    @GET("restful/api/actividad/pablo")
    Call<List<Actividad>> getActividades();

    @GET("restful/api/profesor")
    Call<List<Profesor>> getProfesores();

    @POST("restful/api/actividad")
    Call<Actividad> createActividad(@Body Actividad actividad);

    @DELETE("restful/api/actividad/{id}")
    Call<Actividad> deleteActividad(@Path("id") String id);

    @PUT("restful/api/actividad")
    Call<Actividad>updateActividad(@Body Actividad act);

}
