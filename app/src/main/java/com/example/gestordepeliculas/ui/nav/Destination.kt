package com.example.gestordepeliculas.ui.nav

sealed class Destination(val route:String) {
    object Main_Screen: Destination("Main_Screen")
    object Login_Screen: Destination("Login_Screen")
    object Register_Screen: Destination("Register_Screen")
    object Home_Screen: Destination("Home_Screen")
    object Details_Screen:Destination("Details_Screen")
    object Settings_Screen:Destination("Settings_Screen")
    object Download_Screen:Destination("Download_Screen")
    object Search_Screen:Destination ("Search_Screen")
    object Profile_Screen:Destination ("Profile_Screen")
    object Videoplayer_Screen:Destination("Videoplayer_Screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }

}
