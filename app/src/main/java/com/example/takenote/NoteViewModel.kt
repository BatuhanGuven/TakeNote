package com.example.takenote

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NoteViewModel(private val noteRepository: NoteRepository):ViewModel() {

    val allNotes = noteRepository.allNotes


    fun insert(note: Note) {
        viewModelScope.launch {
            noteRepository.insert(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }

}