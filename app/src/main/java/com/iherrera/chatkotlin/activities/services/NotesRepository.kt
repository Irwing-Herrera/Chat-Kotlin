package com.iherrera.chatkotlin.activities.services

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iherrera.chatkotlin.activities.db.AppDataBase
import com.iherrera.chatkotlin.activities.db.dao.NoteDao
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity

/**
 * Repositorio que accederá a la base de datos para recuperar el DAO
 *
 * @param {Application} application
 */
class NotesRepository(application: Application) {

    private var dao: NoteDao = AppDataBase.getDataBase(application)?.noteDao()!!

    fun getNotes(): LiveData<List<NoteEntity>>? {
        return dao.getAll() ?: MutableLiveData<List<NoteEntity>>()
    }

    fun insertNote(note: NoteEntity) {
        InsertAsyncTask(dao).execute(note)
    }

    private class InsertAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<NoteEntity, Void, Void>() {
        override fun doInBackground(vararg notes: NoteEntity?): Void? {
            for (note in notes) {
                if (note != null) noteDao.insert(note)
            }
            return null
        }
    }
}