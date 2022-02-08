package com.jshme.canvassample

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.rotate
import com.jshme.canvassample.ui.theme.CanvasSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        BanksaladLogo()
                        KakaoTalkLogo()
                    }
                }
            }
        }
    }
}

@Composable
fun BanksaladLogo() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        drawCircle(
            color = Color(0xFF0091F2),
            radius = 30f,
            center = Offset(size.width * .25f, size.height * 0.05f),
        )

        rotate(degrees = 45F) {
            drawRoundRect(
                color = Color(0XFF00C3C3),
                size = Size(50f, 50f),
                topLeft = Offset(x = size.width * .25f, y = size.height * 0.05f),
                cornerRadius = CornerRadius(2f, 2f)
            )
        }

        drawPath(
            // draw for triangle
            path = Path().apply {
                moveTo(size.width * .35f, size.height * .25f)
                lineTo(size.width * .28f, size.height * 0.58f)
                lineTo(size.width * .60f, size.height * 0.50f)
                close()
            },
            color = Color(0xFFE97BBC),
        )

        drawArc(
            color = Color(0XFF00C379),
            startAngle = 360f,
            sweepAngle = 180f,
            useCenter = false,
            size = Size(size.width - 10f, size.height - 10f),
            style = Stroke(width = 45f, cap = StrokeCap.Square)
        )
    }
}

@Composable
fun KakaoTalkLogo() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .padding(16.dp)
    ) {
        drawOval(
            color = Color(0XFF46292A),
            size = Size(size.width, size.height * 0.80f)
        )

        drawPath(
            path = Path().apply {
                moveTo(size.width * .25f, size.height * .70f)
                lineTo(size.width * .20f, size.height * 0.95f)
                lineTo(size.width * .45f, size.height * 0.80f)
                close()
            },
            color = Color(0XFF46292A)
        )

        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 60f
            color = Color.Yellow.toArgb()
        }

        drawContext.canvas.nativeCanvas.drawText("TALK", center.x, center.y, paint)
    }
}
