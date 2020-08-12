package com.iherrera.chatkotlin.activities.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tabla Notes
 */
@Entity(tableName = NoteEntity.TABLE_NAME)
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
    var isfavorite: Boolean? = false
) {
    companion object {
        /**
         * Nombre de tabla
         *
         * @property {String}
         */
        const val TABLE_NAME: String = "notes"
    }

    /**
     * Id de tabla
     *
     * @property {Long}
     */
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}