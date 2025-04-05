package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.action.ExploreUiAction
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.widget.IconTextInfoRow
import com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.home.domain.model.BasicMovieModel
import com.ibrahimutkusarican.cleanarchitecturemovieapp.ui.common.widget.MovieImage

@Composable
fun ComponentExploreMovieForYou(
    modifier: Modifier = Modifier,
    movie: BasicMovieModel,
    handleUiAction: (action: ExploreUiAction) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { handleUiAction(ExploreUiAction.ForYouMovieClickAction(movie.movieId)) }
    ) {
        Text(
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding)),
            text = stringResource(R.string.for_you),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
            elevation = CardDefaults.elevatedCardElevation(dimensionResource(R.dimen.small_card_elevation)),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.small_padding))
            ) {
                Row(
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.small_padding))
                ) {
                    Card(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.home_category_movie_height))
                            .width(dimensionResource(R.dimen.explore_for_you_movie_width)),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.s_medium_border)),
                    ) {
                        MovieImage(
                            imageUrl = movie.moviePosterImageUrl
                        )
                    }
                    Column(
                        modifier = Modifier
                            .height(dimensionResource(R.dimen.home_category_movie_height))
                            .padding(
                                start = dimensionResource(R.dimen.small_padding),
                                top = dimensionResource(R.dimen.large_padding)
                            ),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
                    ) {
                        IconTextInfoRow(
                            icon = painterResource(R.drawable.ic_calender),
                            text = movie.releaseDate
                        )
                        IconTextInfoRow(
                            icon = painterResource(R.drawable.ic_vote_count),
                            text = movie.movieVotePoint
                        )
                        ComponentExploreMovieGenre(
                            modifier = Modifier.weight(1f), genreList = movie.movieGenres
                        )
                    }
                }
                Text(
                    text = movie.movieTitle,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = movie.movieOverview,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComponentExploreMovieForYou() {
    val movie = BasicMovieModel(
        movieId = 1,
        movieTitle = "The Batman",
        movieGenres = listOf("Action", "Drama", "Scientfic"),
        releaseDate = "2022-03-01",
        movieOverview = LoremIpsum(100).values.joinToString(),
        moviePosterImageUrl = null,
        movieBackdropImageUrl = null,
        movieVotePoint = "8.2"
    )

    ComponentExploreMovieForYou(movie = movie)
}