package com.example.pinwheel

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pinwheel.ui.theme.PinWheelTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Pinwheel(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 9000,
                easing = LinearEasing
            )
        )
    )
    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        val cx = size.width / 2
        val cy = size.height / 2
        val nTriangles = 10
        val angle = 360 / nTriangles
        val radius = 450f
        val base = radius * cos(Math.toRadians(angle.toDouble()))
        val colors = listOf(
            Color.Blue,
            Color.Red,
            Color.Green,
            Color.Yellow,
            Color(0xFF99FF99),
            Color.Magenta,
            Color(0xFFA020F0),
            Color(0xFFFFA500),
            Color(0xFFFFF59D),
            Color.Cyan
        )

        for (i in 0 until 10) {
            val bAngle = i.toDouble() * angle
            val hAngle = bAngle + angle
            val ax = cx + base * cos(Math.toRadians(bAngle))
            val ay = cy + base * sin(Math.toRadians(bAngle))
            val bx = cx + radius * cos(Math.toRadians(hAngle))
            val by = cy + radius * sin(Math.toRadians(hAngle))
            val path = Path().apply {
                moveTo(cx, cy)
                lineTo(ax.toFloat(), ay.toFloat())
                lineTo(bx.toFloat(), by.toFloat())
                close()
            }
            rotate(rotation) {
                drawPath(path, color = colors[i])
            }
        }
    }
}

@Preview
@Composable
private fun PinWheelPreview() {
    PinWheelTheme {

        Pinwheel()
    }
}