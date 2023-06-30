package com.example.gestiondeservicosdeentrenamiento.interfaces.presenters

import com.example.gestiondeservicosdeentrenamiento.db.entidades.Servicio

interface IEditarServicioPresenter {
    suspend fun actualizar(servicio: Servicio)
    suspend fun obtenerServicioPorId(id: Int)
}