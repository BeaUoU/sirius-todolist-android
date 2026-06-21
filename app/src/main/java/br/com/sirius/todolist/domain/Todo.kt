package br.com.sirius.todolist.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean
)

//criando 3 objetos falsos para teste
val todo1 = Todo(
    id = 1,
    title = "Estudar Circuitos",
    description = "Estudar para a prova",
    isCompleted = false
)

val todo2 = Todo(
    id = 2,
    title = "Estudar Eletrônica",
    description = "Estudar para a prova",
    isCompleted = true
)

val todo3 = Todo(
    id = 3,
    title = "Estudar Eletromagnetismo",
    description = "Estudar para a prova",
    isCompleted = false
)