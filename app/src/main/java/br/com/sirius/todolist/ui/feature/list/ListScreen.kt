package br.com.sirius.todolist.ui.feature.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.sirius.todolist.data.TodoDatabaseProvider
import br.com.sirius.todolist.data.TodoRepositoryImpl
import br.com.sirius.todolist.domain.Todo
import br.com.sirius.todolist.domain.todo1
import br.com.sirius.todolist.domain.todo2
import br.com.sirius.todolist.domain.todo3
import br.com.sirius.todolist.navigation.AddEditRoute
import br.com.sirius.todolist.ui.UiEvent
import br.com.sirius.todolist.ui.components.TodoItem
import br.com.sirius.todolist.ui.theme.ToDoListTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(
        dao = database.dao
    )
    val viewModel = viewModel<ListViewModel>{
        ListViewModel(
            repository = repository
        )
    }

    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> ->{
                    when(uiEvent.route){
                        is AddEditRoute ->{
                            navigateToAddEditScreen(uiEvent.route.id)
                        }
                    }
                }
                UiEvent.NavigateBack ->{

                }
                is UiEvent.ShowSnackbar ->{

                }
            }
        }
    }

    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    todos: List<Todo>,
    onEvent: (ListEvent) -> Unit = {},
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Minhas tarefas",
                        color = MaterialTheme.colorScheme.tertiary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
       LazyColumn(
           modifier = Modifier
               .padding(paddingValues)
               .consumeWindowInsets(paddingValues = paddingValues),
           contentPadding = PaddingValues(16.dp)
       ){
           item {
               ExtendedFloatingActionButton(
                   onClick = {
                       onEvent(ListEvent.AddEdit(null))
                   },
                   containerColor = MaterialTheme.colorScheme.background,
                   contentColor = MaterialTheme.colorScheme.primary,
                   icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                   text = { Text(text = "Adicionar tarefa") },
                   modifier = Modifier.padding(bottom = 16.dp)
               )
           }
           itemsIndexed(todos){ index, todo ->
               TodoItem(
                   todo = todo,
                   onCompletedChange = {
                       onEvent(ListEvent.CompleteChanged(todo.id, it))
                   },
                   onItemClick = {
                       onEvent(ListEvent.AddEdit(todo.id))
                   },
                   onDeleteClick = {
                       onEvent(ListEvent.Delete(todo.id))
                   },
               )
            if(index<todos.lastIndex){
                Spacer(modifier = Modifier.height(8.dp))
            }
           }
       }
    }
}

@Preview
@Composable
private fun ListContentPreview() {
    ToDoListTheme{
        ListContent(
            todos = listOf(
                todo1,
                todo2,
                todo3,
            ),
            onEvent =  {},
        )
    }

}
