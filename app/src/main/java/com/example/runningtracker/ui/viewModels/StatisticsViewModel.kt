package com.example.runningtracker.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.runningtracker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(val repository: MainRepository) : ViewModel(){

    

}