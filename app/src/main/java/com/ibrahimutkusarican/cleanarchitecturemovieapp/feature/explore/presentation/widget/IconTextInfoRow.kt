package com.ibrahimutkusarican.cleanarchitecturemovieapp.feature.explore.presentation.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.ibrahimutkusarican.cleanarchitecturemovieapp.R

@Composable
fun IconTextInfoRow(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String
) {
    Row(
        modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier,
            painter = icon, tint = MaterialTheme.colorScheme.outline, contentDescription = "text"
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensionResource(R.dimen.small_padding)),
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}
