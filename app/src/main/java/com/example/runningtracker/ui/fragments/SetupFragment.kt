package com.example.runningtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.runningtracker.R
import com.example.runningtracker.databinding.ActivityMainBinding
import com.example.runningtracker.databinding.FragmentSetupBinding
import com.example.runningtracker.others.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.runningtracker.others.Constants.KEY_NAME
import com.example.runningtracker.others.Constants.KEY_WEIGHT
import com.example.runningtracker.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment: Fragment(R.layout.fragment_setup) {

    private var _binding: FragmentSetupBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPref : SharedPreferences

//    private val mainActivityBind = requireActivity() as ActivityMainBinding

    @set:Inject
    var isFirstAppOpen = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }

        binding.tvContinue.setOnClickListener {
            binding.tvContinue.setOnClickListener {
                val success = writePersonalDataToSharedPref()
                if(success) {
                    findNavController().navigate(R.id.action_setupFragment_to_runFragment)
                } else {
                    Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean{
        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()
        if(name.isEmpty() || weight.isEmpty()){
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()

        val toolBarText = "Let's go, $name!"
//        mainActivityBind.tvToolbarTitle.text = toolBarText
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}