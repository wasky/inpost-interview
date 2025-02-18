@file:OptIn(ExperimentalMaterial3Api::class)

package pl.waskysoft.inpost.screen.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.waskysoft.inpost.db.entity.Note
import pl.waskysoft.inpost.ui.theme.AppTheme

@Composable
fun NotesScreenEntry() {
    val viewModel: DetailsViewModel = hiltViewModel()
    val notes = viewModel.loadNotes().collectAsStateWithLifecycle(emptyList())

    val noteDialogOpened = rememberSaveable { mutableStateOf(false) }
    if (noteDialogOpened.value) {
        NoteDialog(dialogOpened = noteDialogOpened) { text ->
            viewModel.addNote(text)
        }
    }

    AppTheme {
        DetailsScreen(
            notes,
            onAddNoteClick = { noteDialogOpened.value = true }
        )
    }
}

@Composable
private fun DetailsScreen(
    notes: State<List<Note>>,
    onAddNoteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { AppBar(onAddNoteClick = onAddNoteClick) }
    ) { innerPadding ->
        DetailsScreenContent(
            notes,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun DetailsScreenContent(
    notes: State<List<Note>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = notes.value,
            key = { note -> note.id }
        ) { note ->
            IgnoredContactItem(note.text)
        }
    }
}

@Composable
private fun IgnoredContactItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(text)
    }
}

@Composable
private fun AppBar(
    onAddNoteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Notes") },
        modifier = modifier,
        actions = {
            IconButton(onClick = onAddNoteClick) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
}

@Composable
private fun NoteDialog(
    dialogOpened: MutableState<Boolean>,
    onConfirmButtonClick: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        icon = { Icon(Icons.Outlined.Edit, contentDescription = null) },
        title = { Text("New note") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it }
            )
        },
        onDismissRequest = { dialogOpened.value = false },
        confirmButton = {
            TextButton(
                onClick = {
                    dialogOpened.value = false
                    onConfirmButtonClick(text)
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogOpened.value = false }) {
                Text("Cancel")
            }
        }
    )
}