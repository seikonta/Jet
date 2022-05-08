package com.kon.dev.jet

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kon.dev.jet.ui.theme.JetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTheme {
                MyApp()
            }
        }
    }


    @Composable
    fun MyApp() {
        Column {
            val topAppBar = CenterAlignedTopAppBar(
                title = {
                    Text(text = "テストアプリ")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "menu"
                        )
                    }
                }
            )

            topAppBar
            TaskGrid(SampleData.TASK_SAMPLE_DATA)
        }
    }

    @Composable
    fun TaskGrid(tasks: List<Task>) {
        LazyColumn {
            var taskRow: MutableList<Task> = mutableListOf()

            items(tasks.size) { index ->
                taskRow.add(tasks[index])
                if (taskRow.size == 2) {
                    TaskRow(task1 = taskRow[0], task2 = taskRow[1])
                    taskRow.clear()
                }
                else if (index + 1 == tasks.size) {
                    TaskRow(task1 = tasks[index])

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TaskRow(task1: Task, task2: Task? = null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(2.dp)
        ) {
//        TaskItem(task = task1)
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .shadow(elevation = 4.dp, MaterialTheme.shapes.medium)
                    .weight(1f, true),
                onClick = {
                    Toast.makeText(this@MainActivity, "id: ${task1.id}", Toast.LENGTH_SHORT).show()
                }
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "${task1.title}: id ${task1.id}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${task1.rate}%",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
            if (task2 != null) {
//            TaskItem(task = task2)
                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .shadow(elevation = 4.dp, MaterialTheme.shapes.medium)
                        .weight(1f, true),
                    onClick = {
                        Toast.makeText(this@MainActivity, "id: ${task2.id}", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "${task2.title}: id ${task2.id}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "${task2.rate}%",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
            else {
                Spacer(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f, true)
                )
            }
        }
    }

    @Composable
    fun TaskItem(task: Task) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .shadow(elevation = 4.dp, MaterialTheme.shapes.medium)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${task.rate}%",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }

    @Preview(
        showBackground = true,
        name = "Light Mode"
    )
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun preview() {
        JetTheme {
            MyApp()
        }
    }

    @Preview(
        name = "TaskRow",
        showBackground = true
    )
    @Composable
    fun previewTaskRow() {
        TaskRow(task1 = SampleData.TASK_SAMPLE_DATA[0], task2 = SampleData.TASK_SAMPLE_DATA[1])
    }

    @Preview(
        name = "TaskItem",
        showBackground = true
    )
    @Composable
    fun previewTaskItem() {
        TaskItem(task = SampleData.TASK_SAMPLE_DATA[0])
    }
}

data class Task(
    val id: Int,
    val title: String,
    val rate: Double
)

object SampleData {
    val TASK_SAMPLE_DATA: List<Task> = listOf(
        Task(0, "title", 2.3),
        Task(1, "title", 2.3),
        Task(2, "title", 2.3),
        Task(3, "title", 2.3),
        Task(4, "title", 2.3),
        Task(5, "title", 2.3),
        Task(6, "title", 2.3),
        Task(7, "title", 2.3),
        Task(8, "title", 2.3),
        Task(9, "title", 2.3),
        Task(10, "title", 2.3),
        Task(11, "title", 2.3),
        Task(12, "title", 2.3),
        Task(13, "title", 2.3),
        Task(14, "title", 2.3),
    )
}