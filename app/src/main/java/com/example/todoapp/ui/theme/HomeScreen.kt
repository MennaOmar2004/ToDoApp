package com.example.todoapp.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.room.TaskViewModel
import com.example.todoapp.room.TasksEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: TaskViewModel= viewModel(),
    ) {
    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())
    val notCompleteTask by viewModel.notCompletedTasks.collectAsState(initial = emptyList())
    val showSheet = remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }
    var taskDate by remember { mutableStateOf("") }
    val taskId by remember { mutableIntStateOf(0) }
    val taskCop by remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "",
                            Modifier.size(36.dp),
                            tint = colorResource(R.color.white)
                        )
                        Spacer(Modifier.padding(10.dp))
                        Text(
                            text = "My Tasks",
                            fontSize = 30.sp,
                            color = colorResource(R.color.white),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.padding(10.dp))
                        IconButton(onClick = {expanded.value=!expanded.value}){
                          Icon(
                              Icons.Default.ArrowDropDown,
                              contentDescription = "",
                              modifier = Modifier.size(50.dp),
                              tint = colorResource(R.color.white)
                          )
                        }

                        if(expanded.value) {
                            DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = {expanded.value=!expanded.value},
//                                modifier = Modifier.background(color = colorResource(R.color.brown)),
                                shape = RectangleShape
                            ) {
                                Card( modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                    onClick = {navController.navigate("FinishedScreen")}
                                ) {
                                    Row (verticalAlignment = Alignment.CenterVertically){
                                        Icon(
                                            Icons.Default.Done,
                                            contentDescription = "",
                                            tint = colorResource(R.color.brown),
                                            modifier = Modifier
                                                .size(30.dp)
                                                .weight(20f),
                                        )
                                        Text(
                                            modifier = Modifier.padding(
                                                vertical = 10.dp
                                                , horizontal = 10.dp),
                                            text = "Finished",
                                            fontStyle = FontStyle.Italic,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 30.sp,
                                            color = colorResource(R.color.brown),
                                        )
                                    }

                                }
                            }
                        }

                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.brown))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(80.dp),
                onClick = { showSheet.value = !showSheet.value },
                shape = RoundedCornerShape(50),
                containerColor = colorResource(R.color.brown)
            )
            {
                Icon(
                    Icons.Default.Add, contentDescription = "",
                    modifier = Modifier.size(50.dp),
                    tint = colorResource(R.color.white)

                )
            }
        }
    )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.Pink40)),
        )
        {
            if (showSheet.value) {
                AlertDialog(
                    onDismissRequest = { },
                    title = {
                        Text(
                            text = "Add New Task",
                            fontSize = 25.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(R.color.brown)
                        )
                    },
                    text = {
                        Column {
                            TextField(
                                value = taskTitle,
                                onValueChange = {title->
                                    taskTitle=title
                                },
                                label = {
                                    Text(
                                        text = "Enter task title",
                                        color = colorResource(R.color.black)
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.padding(15.dp))
                            TextField(
                                value = taskDate,
                                onValueChange = {date->
                                    taskDate=date
                                },
                                label = {
                                    Text(
                                        text = "Enter task Date",
                                        color = colorResource(R.color.black)
                                    )
                                }
                            )
                        }

                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (taskTitle.isNotEmpty()){
                                    viewModel.insertTask(TasksEntity(title = taskTitle, date = taskDate, id =taskId, isComplete = taskCop ))
                                    showSheet.value=false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(colorResource(R.color.brown))
                        ) {
                            Text(
                                text = "Add",
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic,
                            )
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {showSheet.value=false },
                            colors = ButtonDefaults.buttonColors(colorResource(R.color.brown))
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 20.sp,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    },
                    containerColor = colorResource(R.color.Pink40)
                )
            }
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            )
            {
                items(notCompleteTask) { task->
                    var isVisible by remember { mutableStateOf(true) }
//                    LaunchedEffect(task.isComplete) {
//                        if (task.isComplete){
//                            delay(700 )
//                            isVisible=false
//                        }
//                    }
//                    AnimatedVisibility(
//                        visible = isVisible,
//                        exit = fadeOut(animationSpec = tween(durationMillis = 700))
//                    )
                        Row(
                            Modifier
                                .fillMaxSize()
                                .padding(vertical = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Card(
                                modifier = Modifier
                                    .size(width = 300.dp, height = 90.dp)
                                    .padding(start = 20.dp)
                            )
                            {
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    if (isVisible) {
                                        Checkbox(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .padding(horizontal = 20.dp),
                                            checked = task.isComplete,
                                            onCheckedChange = { isComplete ->
                                                val update = task.copy(isComplete = isComplete)
                                                viewModel.updateTask(update)
//                                            if (isComplete) {
////                                                isVisible=false
////                                                CoroutineScope(Dispatchers.Main).launch {
////                                                    delay(500)
////                                                }
//                                            }
                                            },
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = colorResource(
                                                    R.color.brown
                                                )
                                            )
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(horizontal = 20.dp),
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        Text(
                                            text = task.title,
                                            fontSize = 25.sp,
                                            color = colorResource(R.color.brown),
                                            fontStyle = FontStyle.Italic,
                                            fontWeight = FontWeight.SemiBold

                                        )
                                        Text(
                                            text = task.date, fontSize = 20.sp,
                                            color = colorResource(R.color.pink)

                                        )

                                    }


                                }
                            }
                            IconButton(onClick = { viewModel.deleteTask(task) })
                            {
                                Icon(
                                    Icons.Default.Delete, contentDescription = "",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                }
            }
        }
    }
}








@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowPreview(){

}