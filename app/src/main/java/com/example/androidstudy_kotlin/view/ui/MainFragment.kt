package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var navController: NavController

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnViewBinding.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_viewFragment)
        }

        binding.btnDataBinding.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_dataFragment)
        }

        binding.btnRoom.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_roomFragment)
        }

        binding.btnNav.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_firstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}