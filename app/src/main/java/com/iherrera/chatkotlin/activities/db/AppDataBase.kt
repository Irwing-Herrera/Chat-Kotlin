package com.iherrera.chatkotlin.activities.db

import android.content.Context
import androidx.room.*
import com.iherrera.chatkotlin.activities.db.dao.NoteDao
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity

/**
 * Patrón singleton para que la BD sea compartida por cualquier objeto que la utilice
 */
@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "score_database"
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}