package com.paraskcd.notes.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paraskcd.notes.R
import com.paraskcd.notes.components.NoteButton
import com.paraskcd.notes.components.NoteInputText
import com.paraskcd.notes.data.NotesDataSource
import com.paraskcd.notes.models.Note
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa",
        Locale.getDefault())
    return format.format(date)
}

@Composable
fun Home(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column{
        TopAppBar(
            title ={
                Text(text = stringResource(id = R.string.app_name), color = Color.White)
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications , contentDescription = "Icon", tint = Color.White)
            },
            backgroundColor = MaterialTheme.colors.primaryVariant
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = title,
                label = "Title",
                onTextChange = {
                    title = it
                }
            )
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = description,
                label = "Add a note",
                onTextChange = {
                    description = it
                }
            )
            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        //Save to list
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Divider(
                modifier = Modifier.padding(10.dp)
            )
            LazyColumn {
                items(notes) {
                    note ->
                    NoteRow(note = note, onNoteClicked = {
                        onRemoveNote(note)
                        Toast.makeText(context, "Note Removed", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 11.dp,
                    topEnd = 33.dp,
                    bottomStart = 33.dp,
                    bottomEnd = 11.dp
                )
            )
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2, color = Color.White)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1, color = Color.White)
            Text(text = formatDate(note.entryDate.time), style = MaterialTheme.typography.caption, modifier = Modifier.padding(10.dp), color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}