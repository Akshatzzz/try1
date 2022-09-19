package com.example.imagify

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1)
abstract class profileDb: RoomDatabase() {
    abstract fun profiledao():profileDao

    companion object{
        @Volatile
        private var INSTANCE:profileDb?=null

        fun getDatabase(context: Context):profileDb
        {
            val tempInstance= INSTANCE
            if(tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance= Room.databaseBuilder(context.applicationContext,
                profileDb::class.java,
                "app_database").build()

                INSTANCE=instance
                return instance
            }
        }
    }

}