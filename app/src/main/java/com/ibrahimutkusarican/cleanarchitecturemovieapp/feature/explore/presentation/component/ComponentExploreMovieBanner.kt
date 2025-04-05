package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.action.ExploreUiAction
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.widget.PagerDotIndicator
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieImage
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.extension.carouselTransition

@Composable
fun ComponentExploreMovieBanner(
    modifier: Modifier = Modifier,
    bannerMovies: List<BasicMovieModel>,
    handleUiAction: (action: ExploreUiAction) -> Unit
) {
    val state = rememberPagerState { bannerMovies.size }
    HorizontalPager(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.dp_80)),
        pageSpacing = dimensionResource(R.dimen.twelve_padding),
    ) { page ->
        ComponentExploreMovieBannerItem(modifier = Modifier.carouselTransition(
            startValue = 0.85F, page = page, pagerState = state
        ),
            bannerMovie = bannerMovies[page],
            isSelected = state.currentPage == page,
            movieClickAction = { movieId ->
                handleUiAction(ExploreUiAction.BannerMovieClickAction(movieId))
            })
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))

    PagerDotIndicator(state)
}

@Composable
fun ComponentExploreMovieBannerItem(
    modifier: Modifier = Modifier,
    bannerMovie: BasicMovieModel,
    isSelected: Boolean,
    movieClickAction: (movieId: Int) -> Unit
) {
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected) 4.dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    Card(elevation = CardDefaults.cardElevation(animatedElevation),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable {
            movieClickAction(bannerMovie.movieId)
        }) {
        MovieImage(
            modifier = Modifier.fillMaxSize(),
            imageUrl = bannerMovie.moviePosterImageUrl,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
private fun PreviewComponentExploreMovieBanner() {
    ComponentExploreMovieBanner(
        modifier = Modifier.wrapContentHeight(),
        bannerMovies = listOf(
            BasicMovieModel(
                movieId = 1,
                movieTitle = "The Batman",
                movieGenres = listOf("Action", "Drama", "Scientfic"),
                releaseDate = "2022-03-01",
                movieOverview = "",
                moviePosterImageUrl = null,
                movieBackdropImageUrl = null,
                movieVotePoint = "8.2"
            )
        ),
        handleUiAction = {}
    )
}