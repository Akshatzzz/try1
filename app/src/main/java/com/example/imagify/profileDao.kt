package com.example.imagify

import androidx.room.*

@Dao
interface profileDao
{
    @Query("select * from user_details")
    fun showAll():List<Profile>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile:Profile)

    @Query("delete from user_details")
    suspend fun deleteAll()

}
