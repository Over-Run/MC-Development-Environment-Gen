package org.overrun.compose

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun progressBar(
    modifier: Modifier,
    progress: Float,
    color: Color,
    cornerRadius: Dp,
    backgroundColor: Color
) {
    Surface(
        shape =  RoundedCornerShape(cornerRadius),
        color = backgroundColor,
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .drawWithContent {
                drawContent()
                var progressWith = drawContext.size.width * progress
                drawRect(
                    color = color,
                    size = drawContext.size.copy(width = progressWith)
                )
            }
    ) {

    }
}