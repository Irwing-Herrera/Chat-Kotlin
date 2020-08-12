package com.iherrera.chatkotlin.activities.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity

/**
 * Interfaz de métodos con los que accederemos a la entidad en la base de datos.
 */
@Dao
interface NoteDao {

    /**
     * Obtener todas las notas
     *
     * @return {List<NoteEntity>?}
     */
    @Query("SELECT * FROM notes ORDER BY title ASC")
    suspend fun getAllNotes(): List<NoteEntity>?

    /**
     * Insertar nota
     *
     * @param {NoteEntity} note
     * @return {Long}
     */
    @Insert
    suspend fun insertNote(note: NoteEntity): Long

    /**
     * Borrar una nota
     *
     * @param {Long} id
     */
    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)
}