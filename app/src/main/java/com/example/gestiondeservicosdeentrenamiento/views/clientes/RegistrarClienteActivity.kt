package com.example.gestiondeservicosdeentrenamiento.views.clientes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.gestiondeservicosdeentrenamiento.Models.presenters.RegistrarClientePresenter
import com.example.gestiondeservicosdeentrenamiento.databinding.ActivityRegistrarClienteBinding
import com.example.gestiondeservicosdeentrenamiento.db.entidades.*
import com.example.gestiondeservicosdeentrenamiento.interfaces.presenters.IRegistrarClientePresenter
import com.example.gestiondeservicosdeentrenamiento.interfaces.views.RegistrarClienteView
import com.example.gestiondeservicosdeentrenamiento.interfaces.views.RegistrarServicioView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrarClienteActivity : AppCompatActivity(), RegistrarClienteView {
    private lateinit var presenter: IRegistrarClientePresenter
    private lateinit var binding: ActivityRegistrarClienteBinding
    private var id_cliente:Long =0
    private lateinit var clienteCreate: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarClienteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter = RegistrarClientePresenter(this, applicationContext)
        binding.rsBtnGuardar.setOnClickListener {

            val cliente = Cliente()

            GlobalScope.launch {
                presenter.insertarCliente(cliente)
                withContext(Dispatchers.IO) {
                    presenter.insertarPersonales(obtenerPersonales())
                }
                withContext(Dispatchers.IO) {
                    presenter.insertarMedicos(obtenerMedicos())
                }
                withContext(Dispatchers.IO) {
                    presenter.insertarContacto(obtenerContacto())
                }
                withContext(Dispatchers.IO) {
                    presenter.insertarUbicacion(obtenerUbicacion())
                }

            }
            finish()
        }

        binding.rsBtnCancelar.setOnClickListener {
            finish()
        }
    }

    /*
    override fun onBackPressed() {
        lifecycleScope.launch(Dispatchers.IO){
            presenter.eliminar(clienteCreate)
        }
    }

     */


    private fun obtenerContacto():Contacto{
        return Contacto(binding.telefono.text.toString(), binding.correo.text.toString(),id_cliente.toInt())
    }

    private fun obtenerPersonales():DatosPersonales{
        return DatosPersonales(
            binding.nombre.text.toString(),
            binding.apellidoM.text.toString(),
            binding.apellidoP.text.toString(),
            id_cliente.toInt()
        )
    }

    private fun obtenerMedicos():DatosMedicos{
        return DatosMedicos(
            binding.edad.text.toString().toInt(),
            binding.peso.text.toString().toDouble(),
            binding.estatura.text.toString().toDouble(),
            binding.padecimiento.text.toString(),
            binding.medicacion.text.toString(),
            binding.presion.text.toString(),
            id_cliente.toInt()
        )
    }
    private fun obtenerUbicacion():Ubicacion{
        return Ubicacion(
            binding.estado.text.toString(),
            binding.colonia.text.toString(),
            binding.cp.text.toString(),
            id_cliente.toInt()
        )
    }

    override fun mostarIdCliente(id: Long) {
        id_cliente=id
        clienteCreate= Cliente(id.toInt())
        System.out.println("El id del cliente registrado es: "+ id)
    }

}