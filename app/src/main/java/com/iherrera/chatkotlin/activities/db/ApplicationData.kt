package com.iherrera.chatkotlin.activities.db

import com.iherrera.chatkotlin.activities.db.entity.NoteEntity

object ApplicationData {
    private var note: NoteEntity? = null

    fun getNote(): NoteEntity? = note

    fun setNote(note: NoteEntity) {
        this.note = note
    }
}