package com.example.fruitsapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fruitsapp.di.databaseModule
import com.example.fruitsapp.di.networkModule
import com.example.fruitsapp.di.productModule
import com.example.fruitsapp.routes.Screen
import com.example.fruitsapp.ui.theme.EcommerceAppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(listOf(databaseModule, networkModule, productModule))
        }
        setContent {
            EcommerceAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SignupScreen.route) {
        composable(Screen.SignupScreen.route) {
            SignupScreen(navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}
