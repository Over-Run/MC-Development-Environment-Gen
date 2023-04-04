package org.overrun.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun progressBar(
    modifier: Modifier,
    progress: Float,
    color: Color
) {
    Canvas(
        modifier.padding(16.dp).fillMaxWidth()
    ) {
       val canvasWidth = size.width
       val canvasHeigth = size.height
       drawLine(
           color = color,
           start = Offset(0f,0f),
           end = Offset(canvasWidth*(progress/100),0f),
           strokeWidth = 40f,
           cap = StrokeCap.Round
       )
    }
}