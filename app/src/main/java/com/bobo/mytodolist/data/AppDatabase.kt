package com.bobo.mytodolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bobo.mytodolist.utilities.DATABASE_NAME

@Database(entities = [Task::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
//Database: Contains the database holder and serves as the main access point for the underlying connection to your app's persisted, relational data.
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    // 자바의 static과 비슷
    companion object {
        // For Singleton instantiation
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null
//        val migration = object : Migration(1, 2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE task_table ADD COLUMN due_date TEXT DEFAULT ")
//            }
//        }

//        fun getInstance(context: Context) : AppDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//
//        }
//        private fun buildDatabase(context: Context): AppDatabase {
//            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                        WorkManager.getInstance(context).enqueue(request)
//                    }
//                })
//                .build()
//        }


        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // synchronized 블록이 실행되는 동안 this는 lock에 걸린다. 즉, 동기화된(synchronized) 다른 쓰레드에서 사용불가
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
//                    .addMigrations(migration)
//                    .allowMainThreadQueries()
                 .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

