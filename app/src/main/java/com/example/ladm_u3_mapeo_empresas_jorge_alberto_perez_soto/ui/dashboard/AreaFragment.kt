package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.ui.dashboard


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.Area
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.agregarSubDepartamentoActivity
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.databinding.FragmentAreaBinding

class AreaFragment : Fragment() {
    var listaIDs = ArrayList<Int>()

    private var _binding: FragmentAreaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentAreaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mostrarDatosEnLsitaView()
        binding.insertar.setOnClickListener{

            var area = Area(this.requireContext())

            area.descripcion = binding.descripcion.text.toString()
            area.division = binding.division.text.toString()
            area.cantEmpleados =binding.canEmpleados.text.toString().toInt()

            val resultado =area.insertar()//para el main activity la insercion es abstracta
            if (resultado){
                Toast.makeText(this.requireContext(),"SE INSERTO CON EXITO", Toast.LENGTH_LONG).show()
                mostrarDatosEnLsitaView()
                binding.descripcion.setText("")
                binding.division.setText("")
                binding.canEmpleados.setText("")
            }else{
                AlertDialog.Builder(this.requireContext())
                    .setTitle("ERROR")
                    .setMessage("NO SE LOGRO INSERTAR")
                    .show()
            }

        }


        return  root

    }

    fun mostrarDatosEnLsitaView(){
        var listaAreas = Area(this.requireContext()).mostrarTodos()

        var textoAreas = ArrayList<String>()


        listaIDs.clear()
        (0..listaAreas.size-1).forEach{
            val al = listaAreas.get(it)
            textoAreas.add(al.division)
            listaIDs.add(al.idArea)
        }

        binding.lista.adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_list_item_1,textoAreas)
        binding.lista.setOnItemClickListener {adapterView,view,indice,l ->
            val noArea = listaIDs.get(indice)
            val area = Area(this.requireContext()).mostrarArea(noArea)



            AlertDialog.Builder(this.requireContext())
                .setTitle("ATENCION")
                .setMessage("¿Que deseas hacer con el area seleccionada ${area.division}? \nDescripcion: ${area.descripcion}\nEmpleados: ${area.cantEmpleados} \nIdArea: ${area.idArea}")

                .setNegativeButton("Eliminar"){d,i->
                    area.eliminar()
                    mostrarDatosEnLsitaView()
                }

                .setPositiveButton("Actualizar"){d,i->
                    binding.actualizar.isEnabled=true
                    binding.insertar.isEnabled=false

                    binding.descripcion.setText(area.descripcion)
                    binding.division.setText(area.division)
                    binding.canEmpleados.setText(area.cantEmpleados.toString())


                    binding.actualizar.setOnClickListener {
                        var areaAct =Area(this.requireContext())

                        areaAct.idArea=noArea
                        areaAct.descripcion=binding.descripcion.text.toString()
                        areaAct.division=binding.division.text.toString()
                        areaAct.cantEmpleados=binding.canEmpleados.text.toString().toInt()

                        val respuesta =areaAct.actualizar()
                        if(respuesta){
                            AlertDialog.Builder(this.requireContext())
                                .setTitle("Actualizacion Completa")
                                .setMessage("SE LOGRO ACTUALIZAR EL AREA ${areaAct.division}")
                                .show()

                            binding.descripcion.setText("")
                            binding.division.setText("")
                            binding.canEmpleados.setText("")

                            binding.insertar.isEnabled=true
                        }else{
                            AlertDialog.Builder(this.requireContext())
                                .setTitle("ERROR")
                                .setMessage("NO SE LOGRO ACTUALIZAR EL AREA")
                                .show()
                        }


                    }

                }
                .setNeutralButton("+ SUB Departamento"){d,i->
                    var otraVentana = Intent(this.requireContext(), agregarSubDepartamentoActivity::class.java)

                    otraVentana.putExtra("idArea",area.idArea.toString())
                    startActivity(otraVentana)



                }
                .show()
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
