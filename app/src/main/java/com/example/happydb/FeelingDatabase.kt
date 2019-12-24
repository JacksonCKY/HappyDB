package com.example.happydb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Feeling::class], version = 1)

abstract class FeelingDatabase : RoomDatabase(){
    //Instance of DAO
    abstract fun feelingDao() : FeelingDao

    //Ensure only one instance of the database is created
    companion object{
        @Volatile
        private var INSTANCE: FeelingDatabase? = null

        fun getDatabase(context: Context): FeelingDatabase{
            var tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            //create an instance of the database
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, FeelingDatabase::class.java, "feeling_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}