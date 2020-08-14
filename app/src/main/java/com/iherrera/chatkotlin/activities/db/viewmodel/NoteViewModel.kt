package com.iherrera.chatkotlin.activities.db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity
import com.iherrera.chatkotlin.activities.services.NotesRepository

/**
 * View Model que instanciará la clase NotesRepository para recuperar el LiveData e insertar notas.
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NotesRepository(application)
    val notes = repository.getNotes()

    fun insertNote(note: NoteEntity) {
        repository.insertNote(note)
    }

}