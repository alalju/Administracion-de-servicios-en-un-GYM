package com.example.gestiondeservicosdeentrenamiento.views.subscripciones

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.gestiondeservicosdeentrenamiento.R
import com.example.gestiondeservicosdeentrenamiento.databinding.FragmentDatosUbicacionBinding
import com.example.gestiondeservicosdeentrenamiento.databinding.FragmentSeleccionClienteBinding
import kotlinx.coroutines.launch


class SeleccionClienteFragment : DialogFragment() {
    private lateinit var actividad: Activity
    private lateinit var binding: FragmentSeleccionClienteBinding
    private var listener :DataSet? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return lista()
    }

    private fun lista(): Dialog {
        binding = FragmentSeleccionClienteBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        //presernter= SelectClientePresenter(this, requireContext())


        lifecycleScope.launch {
            //presernter.consultarClientes()
        }

        return builder.create()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            if (actividad is DataSet) {
                listener = actividad as DataSet
            }
        } else {
            throw RuntimeException("$context debe implementar OnTextViewDataSet")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSeleccionClienteBinding.inflate(inflater, container, false)
        return binding.root
    }
    interface DataSet {
        //fun onDataSet(cliente: Cliente)
    }

}