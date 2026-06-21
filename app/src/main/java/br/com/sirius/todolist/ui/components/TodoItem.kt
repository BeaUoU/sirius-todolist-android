package br.com.sirius.todolist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.sirius.todolist.domain.Todo
import br.com.sirius.todolist.domain.todo1
import br.com.sirius.todolist.domain.todo2
import br.com.sirius.todolist.ui.theme.ToDoListTheme

@Composable
fun TodoItem(
    todo: Todo,
    onCompletedChange: (Boolean) -> Unit,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 0.dp, // Removido sombra para um visual mais limpo com as novas cores
        color = MaterialTheme.colorScheme.surfaceContainer, // Usa o tom claro dos textfields
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
    ){
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = onCompletedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.secondary, // #8b6f81
                    uncheckedColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            )

            Spacer(modifier= Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                )
            {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground // #8b6f81
                )
                todo.description?.let {
                    Spacer(modifier= Modifier.height(8.dp))

                    Text(
                        text = todo.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier= Modifier.width(8.dp))

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.primary // #db7090
                )
            }
        }
    }
}

@Preview
@Composable
private fun TodoItemPreview() {
    ToDoListTheme{
        TodoItem(
            todo = todo1,
            onCompletedChange = {},
            onDeleteClick = {},
            onItemClick = {},
        )
    }
}
