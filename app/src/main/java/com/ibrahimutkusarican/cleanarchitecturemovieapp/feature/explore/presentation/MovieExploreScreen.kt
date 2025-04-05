package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ibrahimutkusarican.cleanarchitecturemovieapp.core.action.UiState
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.action.ExploreUiAction
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.screen.MovieExploreSuccessScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.ErrorScreen
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.screen.LoadingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieExploreScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val viewModel = hiltViewModel<MovieExploreViewModel>()
    val uiState by viewModel.exploreUiState.collectAsStateWithLifecycle()
    val data by viewModel.exploreInitialData.collectAsStateWithLifecycle()

    when (uiState) {
        is UiState.Error -> {
            ErrorScreen(
                exception = (uiState as UiState.Error).exception,
                tryAgainOnClickAction = {
                    viewModel.handleUiAction(ExploreUiAction.ErrorRetryAction)
                }
            )
        }
        is UiState.Loading -> {
            LoadingScreen()
        }
        is UiState.Success -> {
            MovieExploreSuccessScreen(
                data = data,
                handleUiAction = viewModel::handleUiAction,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope
            )
        }
    }
}