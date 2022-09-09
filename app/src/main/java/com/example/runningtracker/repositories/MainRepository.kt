package com.example.runningtracker.repositories

import com.example.runningtracker.database.Run
import com.example.runningtracker.database.RunDAO
import javax.inject.Inject


class MainRepository @Inject constructor(private val runDao: RunDAO){

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsByDate() = runDao.getAllRunsByDate()

    fun getAllRunsByAvgSpeed() = runDao.getAllRunsByAvdSpeed()

    fun getAllRunsByDistance() = runDao.getAllRunsByDistance()

    fun getAllRunsByTime() = runDao.getAllRunsByTime()

    fun getAllRunsByCaloriesBurned() = runDao.getAllRunsByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalTime() = runDao.getTotalTime()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()


}