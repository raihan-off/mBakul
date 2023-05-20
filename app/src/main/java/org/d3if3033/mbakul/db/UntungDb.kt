package org.d3if3033.mbakul.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UntungEntity::class], version = 1, exportSchema = false)
abstract class UntungDb : RoomDatabase() {
    abstract val dao : UntungDao

    companion object {
        @Volatile
        private var INSTANCE: UntungDb? = null
        fun getInstance(context: Context): UntungDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UntungDb::class.java,
                        "untung.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}