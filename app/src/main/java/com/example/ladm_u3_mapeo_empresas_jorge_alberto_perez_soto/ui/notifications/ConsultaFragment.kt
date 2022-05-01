package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.ui.notifications

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.Area
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.SubDepartamento
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.databinding.FragmentConsultaBinding
import java.io.IOException

class ConsultaFragment (): Fragment() {

    var listaIDs = ArrayList<Int>()
    var listaIDsSTRING = ArrayList<Int>()
    var p =0
    var parametro=""


    private var _binding: FragmentConsultaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentConsultaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //AREA
        binding.division.setOnClickListener {
            mostrarAreaDivision()
        }
        binding.descripcion.setOnClickListener {
            mostrarAreaDescripcion()
        }

        //SUB DEPARTAMENTO
        binding.edificio.setOnClickListener {

             if(binding.buscarparametro.equals("")){
                 AlertDialog.Builder(this.requireContext()).setTitle("Ups")
                     .setMessage("El campo Buscar esta vacio, ingresa un id o una descripcion/division").show()
                 binding.buscarparametro.isFocusable = true

             }else{
               //  p=binding.buscarparametro.text.toString().toInt()
                 mostrarSubEdificio(p)
             }


        }
        binding.subdivision.setOnClickListener {
            if(binding.buscarparametro.equals("")){
                AlertDialog.Builder(this.requireContext()).setTitle("Ups")
                    .setMessage("El campo Buscar esta vacio, ingresa un id o una descripcion/division").show()
                binding.buscarparametro.isFocusable = true


            }else{
                parametro=binding.buscarparametro.text.toString()

                mostrarSubDivision(parametro)
            }

        }
        binding.subdescripcion.setOnClickListener {
            if(binding.buscarparametro.equals("")){
                AlertDialog.Builder(this.requireContext()).setTitle("Ups")
                    .setMessage("El campo Buscar esta vacio, ingresa un id o una descripcion/division").show()
                binding.buscarparametro.isFocusable = true


            }else{
                parametro=binding.buscarparametro.text.toString()

                mostrarSubDescripcion(parametro)
            }
        }


        return root
    }

    //area
    fun mostrarAreaDivision() {
        var listaAreas = Area(this.requireContext()).mostrarTodos()

        var textoAreas = ArrayList<String>()


        listaIDs.clear()
        (0..listaAreas.size - 1).forEach {
            val al = listaAreas.get(it)
            textoAreas.add(al.division)
            listaIDs.add(al.idArea)
        }

        binding.lista.adapter = ArrayAdapter<String>(this.requireContext(), R.layout.simple_list_item_1, textoAreas)
        binding.lista.setOnItemClickListener { adapterView, view, indice, l ->
            val noArea = listaIDs.get(indice)
            val area = Area(this.requireContext()).mostrarArea(noArea)



            androidx.appcompat.app.AlertDialog.Builder(this.requireContext())
                .setTitle("ATENCION")
                .setMessage("Area seleccionada ${area.division} \nDescripcion: ${area.descripcion}\nEmpleados: ${area.cantEmpleados} \nIdArea: ${area.idArea}")
                .setNeutralButton("CERRAR") { d, i ->

                }
                .show()
        }
    }


    fun mostrarAreaDescripcion() {
        var listaAreas = Area(this.requireContext()).mostrarTodos()

        var textoAreas = ArrayList<String>()


        listaIDs.clear()
        (0..listaAreas.size - 1).forEach {
            val al = listaAreas.get(it)
            textoAreas.add(al.descripcion)
            listaIDs.add(al.idArea)
        }

        binding.lista.adapter =
            ArrayAdapter<String>(this.requireContext(), R.layout.simple_list_item_1, textoAreas)
    }



    //Subdepartamento

    fun mostrarSubEdificio(parametro:Int) {
        var listaAreas = SubDepartamento(this.requireContext()).mostraridEdificio(parametro)

        var textoAreas = ArrayList<String>()


        listaIDs.clear()
        (0..listaAreas.size - 1).forEach {
            val al = listaAreas.get(it)
            textoAreas.add(al.idEdificio)
            listaIDs.add(al.idSubdepto)
        }

        binding.lista.adapter =
            ArrayAdapter<String>(this.requireContext(), R.layout.simple_list_item_1, textoAreas)
        binding.lista.setOnItemClickListener { adapterView, view, indice, l ->
            val noSubDept = listaIDs.get(indice)
            val subDep = SubDepartamento(this.requireContext()).mostrarSubdepartamento(noSubDept)



            androidx.appcompat.app.AlertDialog.Builder(this.requireContext())
                .setTitle("ATENCION")
                .setMessage("¿Que deseas hacer con el subdepartamento seleccionado ${subDep.idSubdepto}? \nEdificio: ${subDep.idEdificio}\nPiso: ${subDep.piso}\nIdentificador Area: ${subDep.idArea}")

        }
    }

    fun mostrarSubDescripcion(parametro:String) {
        var listaAreas = SubDepartamento(this.requireContext()).mostrarDescripcion(parametro)

        var textoAreas = ArrayList<Int>()


        listaIDs.clear()
        (0..listaAreas.size - 1).forEach {
            val al = listaAreas.get(it)
            textoAreas.add(al.idSubdepto)
            listaIDs.add(al.idSubdepto)
        }

        binding.lista.adapter =
            ArrayAdapter<Int>(this.requireContext(), R.layout.simple_list_item_1, textoAreas)

        binding.lista.setOnItemClickListener { adapterView, view, indice, l ->
            val noSubDept = listaIDs.get(indice)
            val subDep = SubDepartamento(this.requireContext()).mostrarSubdepartamento(noSubDept)


            androidx.appcompat.app.AlertDialog.Builder(this.requireContext())
                .setTitle("ATENCION")
                .setMessage("¿Que deseas hacer con el subdepartamento seleccionado ${subDep.idSubdepto}? \nEdificio: ${subDep.idEdificio}\nPiso: ${subDep.piso}\nIdentificador Area: ${subDep.idArea}")

        }

    }


    fun mostrarSubDivision(parametro:String) {
        var listaAreas = SubDepartamento(this.requireContext()).mostrarDescripcion(parametro)

        var textoAreas = ArrayList<Int>()


        listaIDs.clear()
        (0..listaAreas.size - 1).forEach {
            val al = listaAreas.get(it)
            textoAreas.add(al.idSubdepto)
            listaIDs.add(al.idSubdepto)
        }

        binding.lista.adapter =
            ArrayAdapter<Int>(this.requireContext(), R.layout.simple_list_item_1, textoAreas)
        binding.lista.setOnItemClickListener { adapterView, view, indice, l ->
            val noSubDept = listaIDs.get(indice)
            val subDep = SubDepartamento(this.requireContext()).mostrarSubdepartamento(noSubDept)



            androidx.appcompat.app.AlertDialog.Builder(this.requireContext())
                .setTitle("ATENCION")
                .setMessage("¿Que deseas hacer con el subdepartamento seleccionado ${subDep.idSubdepto}? \nEdificio: ${subDep.idEdificio}\nPiso: ${subDep.piso}\nIdentificador Area: ${subDep.idArea}")

        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}