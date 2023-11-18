package com.example.proyectofinal_jma.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal_jma.AddNoteHomework
import com.example.proyectofinal_jma.App
import com.example.proyectofinal_jma.Done
import com.example.proyectofinal_jma.EditNoteHomework
import com.example.proyectofinal_jma.LanguageSettings
import com.example.proyectofinal_jma.MainActivity
import com.example.proyectofinal_jma.SettingsApp
import com.example.proyectofinal_jma.ThemesList
import com.example.proyectofinal_jma.Trash
import com.example.proyectofinal_jma.data.NotaEntity
import com.example.proyectofinal_jma.viewModel.NoteDetails

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route
    ){
        var nota=NotaEntity(0,"","","")
        composable(route =AppScreens.MainScreen.route){
            App(modifier = Modifier, navController,
               navigateToItemUpdate={
                    navController.navigate(AppScreens.EditScreen.route)
                    nota=it
                })
        }
        composable(route =AppScreens.AddScreen.route){
            AddNoteHomework(modifier = Modifier,navController)
        }
        composable(route =AppScreens.EditScreen.route){
            EditNoteHomework(modifier = Modifier,navController,nota)
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