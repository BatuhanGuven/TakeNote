package com.example.takenote


class NoteRepository(val noteDao: NoteDao) {

    val allNotes= noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.upsertNote(note)
    }

    suspend fun  delete(note: Note){
        noteDao.deleteNote(note)
    }


}