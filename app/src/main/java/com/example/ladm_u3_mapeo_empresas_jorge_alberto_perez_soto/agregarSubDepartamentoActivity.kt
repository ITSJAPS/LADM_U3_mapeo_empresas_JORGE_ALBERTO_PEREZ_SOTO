package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.databinding.ActivityAgregarSubDepartamentoBinding

class agregarSubDepartamentoActivity : AppCompatActivity() {
    var idArea2=""
    var listaIDs = ArrayList<Int>()
    lateinit var binding :ActivityAgregarSubDepartamentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarSubDepartamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idArea2 = this.intent.extras!!.getString("idArea")!!

        binding.textoarea.setText(idArea2)


        mostrarDatosEnLsitaView()
        binding.insertar.setOnClickListener{


            var SubDept = SubDepartamento(this)

            SubDept.idEdificio = binding.edificio.text.toString()
            SubDept.piso = binding.textopiso.text.toString()
            SubDept.idArea= binding.textoarea.text.toString().toInt()


            val resultado =SubDept.insertar()//para el main activity la insercion es abstracta
            if (resultado){
                Toast.makeText(this,"SE INSERTO CON EXITO", Toast.LENGTH_LONG).show()
                mostrarDatosEnLsitaView()
                binding.edificio.setText("")
                binding.textopiso.setText("")
                binding.textoarea.setText("")
            }else{
                AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("NO SE LOGRO INSERTAR")
                    .show()
            }

        }

        binding.regresar.setOnClickListener{
            finish()
        }


    }


    fun mostrarDatosEnLsitaView(){
        var listaSubDep = SubDepartamento(this).mostrarTodos()

        var textoSubDepartamentos= ArrayList<String>()


        listaIDs.clear()
        (0..listaSubDep.size-1).forEach{
            val al = listaSubDep.get(it)
            textoSubDepartamentos.add(al.idSubdepto.toString())
            listaIDs.add(al.idSubdepto)
        }

        binding.lista.adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1,textoSubDepartamentos)
        binding.lista.setOnItemClickListener {adapterView,view,indice,l ->
            val noSubDept = listaIDs.get(indice)
            val subDep = SubDepartamento(this).mostrarSubdepartamento(noSubDept)



            AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage("¿Que deseas hacer con el subdepartamento seleccionado ${subDep.idSubdepto}? \nEdificio: ${subDep.idEdificio}\nPiso: ${subDep.piso}\nIdentificador Area: ${subDep.idArea}")

                .setNegativeButton("Eliminar"){d,i->
                    subDep.eliminar()
                    mostrarDatosEnLsitaView()
                }

                .setPositiveButton("Actualizar"){d,i->
                    binding.actualizar.isEnabled=true
                    binding.insertar.isEnabled=false
                    binding.regresar.setText("Cancelar")


                    binding.edificio.setText(subDep.idEdificio)
                    binding.textopiso.setText(subDep.piso)
                    binding.textoarea.setText(subDep.idArea.toString())


                    binding.actualizar.setOnClickListener {
                        binding.regresar.setText("Regresar")


                        var subAct = SubDepartamento(this)

                        subAct.idSubdepto=noSubDept
                        subAct.idEdificio=binding.edificio.text.toString()
                        subAct.piso=binding.textopiso.text.toString()
                        subAct.idArea=binding.textoarea.text.toString().toInt()

                        val respuesta =subAct.actualizar()
                        if(respuesta){
                            AlertDialog.Builder(this)
                                .setTitle("Actualizacion Completa")
                                .setMessage("SE LOGRO ACTUALIZAR EL SUB DEPARTAMENTO ${subAct.idSubdepto}")
                                .show()

                            binding.edificio.setText("")
                            binding.textopiso.setText("")
                            binding.textoarea.setText("")

                            binding.insertar.isEnabled=true
                        }else{
                            AlertDialog.Builder(this)
                                .setTitle("ERROR")
                                .setMessage("NO SE LOGRO ACTUALIZAR EL SUBDEPARTAMENTO")
                                .show()
                        }


                    }

                }
                .setNeutralButton("CERRAR"){d,i->

                }
                .show()
        }
    }
}