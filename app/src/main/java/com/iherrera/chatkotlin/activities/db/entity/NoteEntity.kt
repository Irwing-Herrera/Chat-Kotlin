package com.iherrera.chatkotlin.activities.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Tabla Notes
 */
@Entity(
    tableName = "notes",
    indices = [Index("id")]
)
class NoteEntity(
    /**
     * Titulo de la Nota
     *
     * @property {String}
     */
    var title: String? = "",
    /**
     * Descripcion de la Nota
     *
     * @property {String}
     */
    var description: String? = null,
    /**
     * Importancia de Nota
     *
     * @property {Boolean}
     */
    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean? = false
) {
    /**
     * Id de tabla
     *
     * @property {Long}
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}