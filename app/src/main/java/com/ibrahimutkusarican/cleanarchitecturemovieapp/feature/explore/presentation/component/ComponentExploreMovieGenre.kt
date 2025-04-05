package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.EMPTY_STRING

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComponentExploreMovieGenre(
    modifier: Modifier = Modifier,
    genreList: List<String>
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
        maxItemsInEachRow = 4
    ) {
        for (index in 0 until genreList.size + 1) {
            ComponentExploreMovieGenreItem(
                showIcon = index == 0,
                genreText = if (index == 0) EMPTY_STRING else genreList[index - 1]
            )
        }
    }
}

@Composable
fun ComponentExploreMovieGenreItem(
    showIcon: Boolean = false,
    genreText: String
) {
    if (showIcon) {
        Icon(
            modifier = Modifier,
            painter = painterResource(R.drawable.ic_genre),
            tint = MaterialTheme.colorScheme.outline,
            contentDescription = "text"
        )
    } else {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            border = BorderStroke(
                width = dimensionResource(R.dimen.one_dp),
                color = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.x_small_border)),
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.x_small_padding),
                    horizontal = dimensionResource(R.dimen.small_padding)
                ),
                text = genreText,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewComponentExploreMovieGenre() {
    ComponentExploreMovieGenre(
        modifier = Modifier.fillMaxWidth(),
        genreList = listOf(
            "Action", "Adventure", "Comedy", "Drama", "Fantasy", "Horror", "Mystery"
        )
    )
}