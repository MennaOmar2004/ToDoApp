package com.example.todoapp.room

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.FinishedScreen
import com.example.todoapp.ui.theme.HomeScreen

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable(route = "HomeScreen"){
            HomeScreen(navController = navController)

        }
        composable(route = "FinishedScreen"){
            FinishedScreen(navController = navController)
        }

    }
}