package com.example.todoapp.ui.theme

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishedScreen(viewModel: TaskViewModel= viewModel(),navController: NavController){
    val completeTasks by viewModel.completedTasks.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {navController.navigate("HomeScreen")}) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "",
                            Modifier.size(36.dp),
                            tint = colorResource(R.color.white)
                        )
                    }
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        text = "My Finished Tasks",
                        fontSize = 30.sp,
                        color = colorResource(R.color.white),
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(colorResource(R.color.brown))
        )
    })
    {paddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.Pink40)),
        )
        {
            LazyColumn((
                    Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                    )) {
               items(completeTasks){task->
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
                                   Checkbox(
                                       modifier = Modifier
                                           .size(40.dp)
                                           .padding(horizontal = 20.dp),
                                       checked = task.isComplete,
                                       onCheckedChange = {},
                                       colors = CheckboxDefaults.colors(
                                           checkedColor = colorResource(
                                               R.color.brown
                                           )
                                       )
                                   )
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
                       }
               }
            }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowPreview2(){
//FinishedScreen(viewModel ())
}