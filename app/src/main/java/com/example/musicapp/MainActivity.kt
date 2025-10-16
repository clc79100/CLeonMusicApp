package com.example.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.musicapp.routes.AlbumDetailScreenRoute
import com.example.musicapp.routes.HomeScreenRoute
import com.example.musicapp.screens.AlbumDetailScreen
import com.example.musicapp.screens.HomeScreen
import com.example.musicapp.ui.theme.MusicAppTheme
//TODO: Optimizacion de Icono Play. Documnetacion de secciones y componentes
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreenRoute
                    ){
                        composable <HomeScreenRoute>{
                            HomeScreen(innerPadding, navController)
                        }

                        composable<AlbumDetailScreenRoute>{ backStack ->
                            val args = backStack.toRoute<AlbumDetailScreenRoute>()
                            AlbumDetailScreen(innerPadding,navController, args.id)
                        }
                    }

                }
            }
        }
    }
}