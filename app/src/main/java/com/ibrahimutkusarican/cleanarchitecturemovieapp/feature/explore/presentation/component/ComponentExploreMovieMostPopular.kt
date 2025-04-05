package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.presentation.MovieCategory
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.seeall.data.SeeAllType

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ComponentExploreMovieMostPopular(
    modifier: Modifier = Modifier,
    movies: List<BasicMovieModel>,
    seeAllClickAction: (seeAllType: SeeAllType) -> Unit = {},
    movieClickAction: (movieId: Int, sharedAnimationKey: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    MovieCategory(
        modifier = modifier,
        seeAllMovieType = SeeAllType.SeeAllMovieType.Popular,
        title = stringResource(R.string.most_popular),
        movies = movies,
        seeAllClickAction = seeAllClickAction,
        movieClickAction = movieClickAction,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope
    )
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun PreviewComponentExploreMovieMostPopular() {
    val mockData = BasicMovieModel(
        movieId = 1,
        movieTitle = "The Batman",
        movieGenres = listOf("Action", "Drama", "Scientfic"),
        releaseDate = "2022-03-01",
        movieOverview = LoremIpsum(100).values.joinToString(),
        moviePosterImageUrl = null,
        movieBackdropImageUrl = null,
        movieVotePoint = "8.2"
    )

    SharedTransitionLayout {
        AnimatedContent(
            targetState = true,
            transitionSpec = { fadeIn() with fadeOut() }
        ) { _ ->
            ComponentExploreMovieMostPopular(
                movies = listOf(mockData),
                seeAllClickAction = {},
                movieClickAction = { _, _ -> },
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedContentScope = this@AnimatedContent,
            )
        }
    }
}