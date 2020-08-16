package com.iherrera.chatkotlin.activities.db.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity
import com.iherrera.chatkotlin.activities.services.NotesRepository

/**
 * View Model que instanciará la clase NotesRepository para recuperar el LiveData e insertar notas.
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Repositorio de Notas
     *
     * @property {NotesRepository}
     */
    private val repository = NotesRepository(application)
    /**
     * Lista de Notas en observer
     *
     * @property {LiveData<List<NoteEntity>>?}
     */
    val notes = repository.getNotes()

    /**
     * Insertar notas en BD
     *
     * @param {NoteEntity} note
     */
    fun insertNote(note: NoteEntity) {
        repository.insertNote(note)
    }

}