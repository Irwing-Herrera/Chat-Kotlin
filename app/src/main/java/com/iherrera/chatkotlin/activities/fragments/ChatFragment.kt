package com.iherrera.chatkotlin.activities.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.iherrera.chatkotlin.R
import com.iherrera.chatkotlin.activities.db.entity.NoteEntity
import com.iherrera.chatkotlin.activities.db.viewmodel.NoteViewModel
import kotlin.random.Random

class ChatFragment : Fragment() {

    private val noteViewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_chat, container, false)

        val btn = rootView.findViewById<Button>(R.id.buttonAddNote)

        btn.setOnClickListener {
            noteViewModel.insertNote(
                NoteEntity(
                    "Nota ${Random.nextInt(0, 1000)}",
                    "descripcion",
                    Random.nextInt(0, 1) > 0
                )
            )
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.notes!!.observe(viewLifecycleOwner, Observer<List<NoteEntity>> { notes ->
            Log.w("consola", "Notas: ${notes.size}")
        })
    }

}