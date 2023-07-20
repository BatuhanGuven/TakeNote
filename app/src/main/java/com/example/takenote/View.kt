package com.example.takenote

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(noteViewModel: NoteViewModel) {
    val notesState: State<List<Note>> = noteViewModel.allNotes.collectAsState(emptyList())
    val notes = notesState.value.toList()
    var isFabClicked= remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Notlarım") },modifier= Modifier)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { isFabClicked.value = ! isFabClicked.value },
                  
                    content = {
                        Icon(imageVector = Icons.Default.Add, contentDescription ="add" )
                    }
                )
            },
            content = {

                Box(modifier = Modifier.padding(paddingValues = it)){
                    if (isFabClicked.value) {
                        SimpleTextField(noteViewModel)
                    } else {
                        NoteList(notes = notes,noteViewModel)
                    }
                }


            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(noteViewModel: NoteViewModel) {
    var title = remember{mutableStateOf("")}
    var content = remember{mutableStateOf("")}
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = title.value, onValueChange ={title.value=it}, label = { Text(text = "Title")} , modifier = Modifier.padding(4.dp))
            TextField(value = content.value, onValueChange ={content.value=it}, label = { Text(text = "Content")}, modifier = Modifier.padding(4.dp))

            Button(onClick = {
                addItem(noteViewModel,title.value, content.value)
                             Toast.makeText(context,"Not Eklendi",Toast.LENGTH_SHORT).show()},
                modifier = Modifier.padding()
            ) {
                Text(text = "Add Note")
            }
        }

    }


}
fun deleteItem(viewModel: NoteViewModel,note: Note){

    viewModel.delete(note)

}

fun addItem(viewModel: NoteViewModel,title:String,content:String){
    viewModel.insert(Note(0, title, content))
}

@Composable
fun NoteList(notes: List<Note>,noteViewModel: NoteViewModel) {
    LazyColumn {
        items(notes){
            NoteItem(note = it,noteViewModel)
        }
    }
}



@Composable
fun NoteItem(note: Note,noteViewModel: NoteViewModel) {
    val isDelete = remember{ mutableStateOf(false)}
    Column() {
        Card(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                Column() {
                    Text(text = "Title: ${note.title}",Modifier.padding(2.dp))
                    Text(text = "Content: ${note.content}",Modifier.padding(2.dp))
                }
                Button(onClick = { deleteItem(noteViewModel,note)}, modifier = Modifier.background(Color.Transparent), colors = ButtonDefaults.buttonColors(
                    Color.Transparent)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            
        }

    }
}