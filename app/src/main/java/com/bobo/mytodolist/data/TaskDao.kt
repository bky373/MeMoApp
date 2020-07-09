package com.bobo.mytodolist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

// PASS

/**
 * The Data Access Object for the Task class.
 * (db내 민원처리전담소(3차 접수창구), 물품저장소(repository)로 부터 민원접수받음)
 */
//DAO: Contains the methods used for accessing the database.
//The app uses each DAO to get entities from the database and save any changes to those entities back to the database.
@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table ORDER BY `order`")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE is_deleted = 0 ORDER BY `order`")
    fun getNotFinishedTasks() : LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    fun getTask(taskId: Long): LiveData<Task>

    @Query("SELECT MAX(`order`) FROM task_table")
    fun getLargestOrder(): Long

    @Query("SELECT * FROM task_table WHERE is_deleted = 1")
    fun getFinishedTasks() : LiveData<List<Task>>

    @Query("DELETE FROM task_table WHERE is_deleted = 1")
    fun emptyBin()

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복 ID일 경우 교체
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAllTasks(tasks: List<Task>)

}