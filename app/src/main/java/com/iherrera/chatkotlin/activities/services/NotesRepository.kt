package com.iherrera.chatkotlin.activities.services

import android.content.Context
import com.iherrera.chatkotlin.activities.db.AppDataBase
import com.iherrera.chatkotlin.activities.db.dao.NoteDao
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity

/**
 * Repositorio que accederá a la base de datos para recuperar el DAO
 */
class NotesRepository(context: Context) {

    private var dao: NoteDao = AppDataBase.getDataBase(context)?.noteDao()!!

    suspend fun getAllNotes(): List<NoteEntity> = dao.getAllNotes() ?: ArrayList()

    suspend fun insertNote(note: NoteEntity): Long = dao.insertNote(note)

    suspend fun deleteNote(id: Long) = dao.deleteNote(id)

}