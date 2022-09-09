package com.example.runningtracker.ui.viewModels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningtracker.database.Run
import com.example.runningtracker.others.SortTypes
import com.example.runningtracker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(){

    private val runsSortedByDate = repository.getAllRunsByDate()
    private val runsSortedByDistance = repository.getAllRunsByDistance()
    private val runsSortedByAvgSpeed = repository.getAllRunsByAvgSpeed()
    private val runsSortedByTime = repository.getAllRunsByTime()
    private val runsSortedByCaloriesBurned = repository.getAllRunsByCaloriesBurned()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortTypes.DATE

    init {
        runs.addSource(runsSortedByDate){result ->
            if(sortType == SortTypes.DATE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed){result ->
            if(sortType == SortTypes.AVG_SPEED){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance){result ->
            if(sortType == SortTypes.DISTANCE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByTime){result ->
            if(sortType == SortTypes.TIME){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByCaloriesBurned){result ->
            if(sortType == SortTypes.CALORIES_BURNED){
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortTypes) = when(sortType) {
        SortTypes.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortTypes.TIME -> runsSortedByTime.value?.let { runs.value = it }
        SortTypes.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortTypes.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortTypes.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

     fun insertRun(run: Run) = viewModelScope.launch {
         repository.insertRun(run)
     }

}