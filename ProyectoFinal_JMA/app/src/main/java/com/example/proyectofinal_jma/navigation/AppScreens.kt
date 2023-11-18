package com.example.proyectofinal_jma.navigation

sealed class AppScreens(val route:String){
    object MainScreen: AppScreens("MainScreen")
    object SettingsScreen: AppScreens("SettingsScreen")
    object AddScreen: AppScreens("AddScreen")
    object DoneScreen: AppScreens("DoneScreen")
    object LanguageScreen: AppScreens("LanguageScreen")
    object ThemesScreen: AppScreens("ThemesScreen")
    object TrashScreen: AppScreens("TrashScreen")
    object EditScreen: AppScreens("EditScreen")
}
