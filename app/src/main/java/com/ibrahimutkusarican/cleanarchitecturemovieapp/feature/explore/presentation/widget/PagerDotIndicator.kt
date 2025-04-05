package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R
import com.ibrahimutkusarican.cleanarchitecturemovieapp.utils.Constants.BANNER_EXPLORE_PAGER_DOT_INDICATOR_COUNT

@Composable
fun PagerDotIndicator(state: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(BANNER_EXPLORE_PAGER_DOT_INDICATOR_COUNT) { index ->
            val isSelected = (state.currentPage % 3 == index)

            val dotWidth by animateDpAsState(
                targetValue = if (isSelected) dimensionResource(R.dimen.pager_indicator_big_dot_width)
                else dimensionResource(R.dimen.pager_indicator_dot_width),
                label = "DotWidth"
            )

            val dotColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline,
                label = "DotColor"
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(dimensionResource(R.dimen.pager_indicator_dot_height))
                    .width(dotWidth)
                    .clip(CircleShape)
                    .background(dotColor)
            )
        }
    }
}