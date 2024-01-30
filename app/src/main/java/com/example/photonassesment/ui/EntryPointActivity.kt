package com.example.photonassesment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.photonassesment.ui.feature.school_details.SchoolDetailsScreen
import com.example.photonassesment.ui.feature.school_details.SchoolDetailsViewModel
import com.example.photonassesment.ui.feature.schools.SchoolsListScreen
import com.example.photonassesment.ui.feature.schools.SchoolViewModel
import com.example.photonassesment.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow

// Single Activity per app
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                PhotonApp()
            }
        }
    }
}

@Composable
private fun PhotonApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.SCHOOLS_LIST) {
        composable(route = NavigationKeys.Route.SCHOOLS_LIST) {
            SchoolsScreenDestination(navController)
        }
        composable(
            route = NavigationKeys.Route.SCHOOL_DETAILS,
            arguments = listOf(navArgument(NavigationKeys.Arg.SCHOOL_DETAILS) {
                type = NavType.StringType
            })
        ) {
            SchoolDetailsDestination()
        }
    }
}

@Composable
private fun SchoolsScreenDestination(navController: NavHostController) {
    val viewModel: SchoolViewModel = hiltViewModel()
    SchoolsListScreen(
        state = viewModel.state,
        effectFlow = viewModel.effects.receiveAsFlow(),
        onNavigationRequested = { itemId ->
            navController.navigate("${NavigationKeys.Route.SCHOOLS_LIST}/${itemId}")
        })
}

@Composable
private fun SchoolDetailsDestination() {
    val viewModel: SchoolDetailsViewModel = hiltViewModel()
    SchoolDetailsScreen(viewModel.state)
}

object NavigationKeys {

    object Arg {
        const val SCHOOL_DETAILS = "schoolDetails"
    }

    object Route {
        const val SCHOOLS_LIST = "schools_list"
        const val SCHOOL_DETAILS = "$SCHOOLS_LIST/{${Arg.SCHOOL_DETAILS}}"
    }

}