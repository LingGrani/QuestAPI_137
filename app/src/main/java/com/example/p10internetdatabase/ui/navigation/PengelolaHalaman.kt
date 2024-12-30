package com.example.p10internetdatabase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p10internetdatabase.ui.view.DeestinasiEntry
import com.example.p10internetdatabase.ui.view.DestinasiHome
import com.example.p10internetdatabase.ui.view.EntryMhsScreen
import com.example.p10internetdatabase.ui.view.HomeScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()

) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToltemEntry = { navController.navigate(DeestinasiEntry.route) },
                onDetailClick = {
                }
            )
        }
        composable(DeestinasiEntry.route) {
            EntryMhsScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) { inclusive = true }
                    }
                }
            )
        }
    }
}