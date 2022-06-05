package com.kon.dev.jet

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kon.dev.jet.ui.theme.JetTheme
import com.kon.dev.jet.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
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
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val items = listOf(
            Icons.Default.Favorite,
            Icons.Default.Face,
            Icons.Default.Email
        )

        val selectedItem = remember { mutableStateOf(items[0]) }

        ModalNavigationDrawer(
            drawerContent = {
                Text(
                    text = "タイトル",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            },
            drawerState = drawerState,
            content =  {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = "テストアプリ")
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(
                                        imageVector = Icons.Rounded.Menu,
                                        contentDescription = "menu"
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        LargeFloatingActionButton(
                            onClick = {}
                        ) {
                            Icon (
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Add",
                                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize)
                            )
                        }
                    }
                ) {
                    TaskGrid(SampleData.TASK_SAMPLE_DATA)
                }
            }
        )
        

    }

    @Composable
    fun TaskGrid(tasks: List<Task>) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(tasks) { task: Task ->
                TaskItem(task = task)
            }
        }
    }

    @Composable
    fun TaskItem(task: Task) {
        OutlinedCard(
            modifier = Modifier.padding(8.dp),
            onClick = {
                Toast.makeText(this, "id: ${task.id}", Toast.LENGTH_SHORT).show()
            }
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "${task.title}: id ${task.id}",
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
        Task(15, "title", 2.3),
        Task(16, "title", 2.3),
        Task(17, "title", 2.3),
        Task(18, "title", 2.3),
        Task(19, "title", 2.3),
        Task(20, "title", 2.3),
        Task(21, "title", 2.3),
        Task(22, "title", 2.3),
        Task(23, "title", 2.3),
        Task(24, "title", 2.3),
        Task(25, "title", 2.3),
        Task(26, "title", 2.3),
        Task(27, "title", 2.3),
        Task(28, "title", 2.3),
        Task(29, "title", 2.3),
        Task(30, "title", 2.3),
        Task(31, "title", 2.3),
        Task(32, "title", 2.3),
        Task(33, "title", 2.3),
        Task(34, "title", 2.3),
        Task(35, "title", 2.3),
        Task(36, "title", 2.3),
    )
}