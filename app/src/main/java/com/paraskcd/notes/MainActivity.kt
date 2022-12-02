package com.paraskcd.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paraskcd.notes.models.Note
import com.paraskcd.notes.screens.Home
import com.paraskcd.notes.screens.HomeViewModel
import com.paraskcd.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val homeViewModel: HomeViewModel by viewModels()
                    NotesApp(homeViewModel = homeViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(homeViewModel: HomeViewModel = viewModel()) {
    val noteList = homeViewModel.getAllNotes()

    Home(
        notes = noteList,
        onAddNote = { homeViewModel.addNote(it) },
        onRemoveNote = { homeViewModel.removeNote(it) }
    )
}