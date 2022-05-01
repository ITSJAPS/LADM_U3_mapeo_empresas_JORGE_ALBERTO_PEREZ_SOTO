package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.databinding.FragmentConsultaBinding
import com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto.databinding.FragmentHomeBinding
import java.text.DateFormat

import java.util.*

class InicioFragment : Fragment() {

    var currentDateTimeString: String = DateFormat.getDateTimeInstance().format(Date())


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

      binding.fechatexto.setText(currentDateTimeString)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}