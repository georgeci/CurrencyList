package com.georgeci.purevm.sample.root.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.georgeci.purevm.sample.presentation.feature.details.DetailsComponent
import com.georgeci.purevm.sample.presentation.feature.details.DetailsViewModel
import com.georgeci.purevm.sample.presentation.feature.list.ListComponent
import com.georgeci.purevm.sample.presentation.feature.list.ListViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            val viewModel = hiltViewModel<ListViewModel>()
            ListComponent(
                viewModel = viewModel,
                onItemClick = {
                    navController.navigate("details/${it.id}/${it.name}")
                },
            )
        }
        composable("details/{code}/{name}") { backStackEntry ->
            val code = backStackEntry.arguments?.getString("code")
            val name = backStackEntry.arguments?.getString("name")
            val viewModel: DetailsViewModel = hiltViewModel(
                creationCallback = { factory: DetailsViewModel.Factory ->
                    factory.create(name!!, code!!)
                }
            )
            DetailsComponent(viewModel)
        }
    }
}