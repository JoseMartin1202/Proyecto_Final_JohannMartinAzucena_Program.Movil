package com.example.proyectofinal_jma.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal_jma.AddNoteHomework
import com.example.proyectofinal_jma.App
import com.example.proyectofinal_jma.Done
import com.example.proyectofinal_jma.LanguageSettings
import com.example.proyectofinal_jma.MainActivity
import com.example.proyectofinal_jma.SettingsApp
import com.example.proyectofinal_jma.ThemesList
import com.example.proyectofinal_jma.Trash

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route
    ){
        composable(route =AppScreens.MainScreen.route){
            App(modifier = Modifier, navController,
               navigateToItemUpdate={
                    navController.navigate(AppScreens.AddScreen.route)
                })
        }
        composable(route =AppScreens.AddScreen.route){
            AddNoteHomework(modifier = Modifier,navController)
        }
        composable(route =AppScreens.DoneScreen.route){
            Done(modifier = Modifier,navController)
        }
        composable(route =AppScreens.LanguageScreen.route){
            LanguageSettings(modifier = Modifier,navController)
        }
        composable(route =AppScreens.TrashScreen.route){
            Trash(modifier = Modifier,navController)
        }
        composable(route =AppScreens.ThemesScreen.route){
            ThemesList(modifier = Modifier,navController)
        }
        composable(route =AppScreens.SettingsScreen.route){
            SettingsApp(modifier = Modifier,navController)
        }
    }
}