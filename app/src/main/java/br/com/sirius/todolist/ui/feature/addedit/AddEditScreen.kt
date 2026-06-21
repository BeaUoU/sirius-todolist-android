package br.com.sirius.todolist.ui.feature.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.sirius.todolist.data.TodoDatabaseProvider
import br.com.sirius.todolist.data.TodoRepositoryImpl
import br.com.sirius.todolist.ui.UiEvent
import br.com.sirius.todolist.ui.theme.ToDoListTheme

@Composable
fun AddEditScreen(
    id: Long?,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(
        dao = database.dao
    )
    val viewModel = viewModel<AddEditViewModel>{
        AddEditViewModel(
            id = id,
            repository = repository
        )
    }

    val title = viewModel.title
    val description = viewModel.description

    val snackbarHostState = remember() {
        SnackbarHostState()
    }

    LaunchedEffect(Unit){
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.ShowSnackbar ->{
                    snackbarHostState.showSnackbar(
                        message = uiEvent.message
                    )
                }
                is UiEvent.NavigateBack ->{
                    navigateBack()
                }
                is UiEvent.Navigate<*> ->{

                }
            }
        }
    }

    AddEditContent(
        title = title,
        description = description,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContent(
    title: String,
    description: String?,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit = {},
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalhes",
                        color = MaterialTheme.colorScheme.tertiary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(AddEditEvent.Save)
                },
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        },

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .padding(16.dp)
        ) {
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = {
                    onEvent(
                        AddEditEvent.TitleChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Title")
                },
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = description ?: "",
                onValueChange = {
                    onEvent(
                        AddEditEvent.DescriptionChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Description (Optional)")
                },
                colors = textFieldColors
            )
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    ToDoListTheme{
        AddEditContent(
            title = "",
            description = null,
            snackbarHostState = SnackbarHostState(),
            onEvent = {},
        )
    }
}
