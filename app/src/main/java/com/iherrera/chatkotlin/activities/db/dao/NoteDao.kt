package com.iherrera.chatkotlin.activities.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity

/**
 * Interfaz de métodos con los que accederemos a la entidad en la base de datos.
 * DAO -> objetos de acceso a datos
 */
@Dao
interface NoteDao {

    /**
     * Obtener todas las notas
     *
     * @return {LiveData<List<NoteEntity>>}
     */
    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getAll(): LiveData<List<NoteEntity>>

    /**
     * Obtener todas las notas favoritas
     *
     * @return {LiveData<List<NoteEntity>>}
     */
    @Query("SELECT * FROM notes WHERE favorite == 1 ORDER BY title ASC")
    fun getAllFavorites(): LiveData<List<NoteEntity>>

    /**
     * Insertar nota
     *
     * @param {NoteEntity} note
     * @return {Long}
     */
    @Insert
    fun insert(note: NoteEntity): Long

    /**
     * Borrar una nota
     *
     * @param {Long} id
     */
    @Query("DELETE FROM notes WHERE id = :id")
    fun delete(id: Long)

    /**
     * Borrar todas las nota
     */
    @Query("DELETE FROM notes")
    fun deleteAll()
}