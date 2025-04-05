package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.domain.model.ExploreDataModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.action.ExploreUiAction
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component.ComponentExploreMovieBanner
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component.ComponentExploreMovieForYou
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component.ComponentExploreMovieMostPopular
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.component.MovieSearchBar

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieExploreSuccessScreen(
    data: ExploreDataModel,
    handleUiAction: (action: ExploreUiAction) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(R.dimen.medium_padding))
    ) {
        MovieSearchBar(
            modifier = Modifier.padding(dimensionResource(R.dimen.large_padding)),
            searchText = "",
            showFilterIcon = true,
            isEnable = false,
            onClickAction = {
                handleUiAction(ExploreUiAction.SearchBarClickAction(data.forYouMovie?.movieId))
            })
        ComponentExploreMovieBanner(
            modifier = Modifier.wrapContentHeight(),
            bannerMovies = data.bannerMovies,
            handleUiAction = handleUiAction
        )
        data.forYouMovie?.let {
            ComponentExploreMovieForYou(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.large_padding),
                    end = dimensionResource(R.dimen.large_padding),
                    top = dimensionResource(R.dimen.medium_padding)
                ), movie = data.forYouMovie, handleUiAction = handleUiAction
            )
        }

        ComponentExploreMovieMostPopular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(R.dimen.large_padding),
                    vertical = dimensionResource(R.dimen.medium_padding)
                ),
            movies = data.popularMovies,
            seeAllClickAction = { seeAllType ->
                handleUiAction(ExploreUiAction.SeeAllClickAction(seeAllType))
            },
            movieClickAction = { movieId,sharedAnimationKey ->
                handleUiAction(ExploreUiAction.MovieClickAction(movieId,sharedAnimationKey))
            },
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun MovieExploreSuccessScreenPreview() {
    val mockData = ExploreDataModel(
        forYouMovie = BasicMovieModel(
            movieId = 1,
            movieTitle = "The Batman",
            movieGenres = listOf("Action", "Drama", "Scientfic"),
            releaseDate = "2022-03-01",
            movieOverview = LoremIpsum(100).values.joinToString(),
            moviePosterImageUrl = null,
            movieBackdropImageUrl = null,
            movieVotePoint = "8.2"
        )
    )
    SharedTransitionLayout {
        AnimatedContent(
            targetState = true,
            transitionSpec = { fadeIn() with fadeOut() }
        ) { _ ->
            MovieExploreSuccessScreen(
                data = mockData,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedContentScope = this@AnimatedContent,
                handleUiAction = {}
            )
        }
    }
}