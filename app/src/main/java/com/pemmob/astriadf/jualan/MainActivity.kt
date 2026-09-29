package com.pemmob.astriadf.jualan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.pemmob.astriadf.jualan.ui.screen.DaftarProdukScreen
import com.pemmob.astriadf.jualan.ui.screen.DetailProductScreen
import com.pemmob.astriadf.jualan.ui.screen.HubungiKamiScreen
import com.pemmob.astriadf.jualan.ui.theme.JualanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JualanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "daftar_produk"
                    ) {
                        composable("daftar_produk") {
                            DaftarProdukScreen(navController = navController)
                        }
                        composable(
                            route = "detail/{productId}",
                            arguments = listOf(
                                navArgument("productId") { type = NavType.IntType }
                            )
                        ) { backStackEntry ->
                            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                            DetailProductScreen(
                                productId = productId,
                                navController = navController
                            )
                        }
                        composable("hubungi_kami") {
                            HubungiKamiScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}