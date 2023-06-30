package com.example.gestiondeservicosdeentrenamiento.db.daos

import androidx.room.*
import com.example.gestiondeservicosdeentrenamiento.db.entidades.Subscripcion

@Dao
interface SubscripcionDao {
    @Insert
    fun insert(subscripcion: Subscripcion)

    @Update
    fun update(subscripcion: Subscripcion)

    @Delete
    fun delete(subscripcion: Subscripcion)

    @Query("SELECT * FROM subscripcion WHERE id_suscripcion = :id")
    fun getSubscripcionById(id: Int): Subscripcion

    @Query("SELECT * FROM subscripcion")
    fun getAllSubscripciones(): List<Subscripcion>

    @Query("SELECT subscripcion.*, servicio.nombre FROM subscripcion INNER JOIN servicio ON subscripcion.id_servicio = servicio.id_servicio WHERE subscripcion.id_suscripcion = :idSuscripcion")
    fun getSubscriptionWithServiceName(idSuscripcion: Int): SubscripcionServicio

}