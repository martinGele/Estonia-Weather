package com.martin.weatherestonia.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.martin.weatherestonia.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(WeatherFourDays::class, WeatherCurrent::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "appData.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
//    private class WordDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//        /**
//         * Override the onOpen method to populate the database.
//         * For this sample, we clear the database every time it is created or opened.
//         */
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            // If you want to keep the data through app restarts,
//            // comment out the following line.
//            INSTANCE?.let { database ->
//                scope.launch(Dispatchers.IO) {
//                    populateDatabase(database.wordDao())
//                }
//            }
//        }
//    }
//
//    /**
//     * Populate the database in a new coroutine.
//     * If you want to start with more words, just add them.
//     */
//    suspend fun populateDatabase(weatherDao: WeatherDao) {
//        // Start the app with a clean database every time.
//        // Not needed if you only populate on creation.
//
//    }

}