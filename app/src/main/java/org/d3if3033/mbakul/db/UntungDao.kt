package org.d3if3033.mbakul.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UntungDao {

    @Insert
    fun insert(untung: UntungEntity)

    @Query("SELECT * FROM untung ORDER BY id DESC")
    fun getLastUntung():LiveData<List<UntungEntity>>

    @Query("DELETE from untung")
    fun clearData()
}