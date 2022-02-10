package com.jshme.canvassample

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
                        SemiRoundSeekBar()
                    }
                }
            }
        }
    }
}

@Composable
fun SemiRoundSeekBar() {
    val animatedValue = remember { Animatable(0f) }
    val textState = remember { mutableStateOf(0) }

    LaunchedEffect(animatedValue) {
        animatedValue.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing))
    }

    val scoreTextPaint = Paint().apply {
        textAlign = Paint.Align.CENTER
        textSize = 100f
        color = Color.Gray.toArgb()
    }

    Column {
        Box(
            modifier = Modifier.size(400.dp, 200.dp)
        ) {
            Canvas(modifier = Modifier
                .size(400.dp)
                .padding(16.dp)
            ) {
                drawArc(
                    color = Color.LightGray,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(30f, 10f),
                    size = Size(900.dp.value, 900.dp.value),
                    style = Stroke(width = 40f, cap = StrokeCap.Round)
                )

                drawArc(
                    color = Color.Green,
                    startAngle = 180f,
                    sweepAngle = textState.value * animatedValue.value,
                    useCenter = false,
                    topLeft = Offset(30f, 10f),
                    size = Size(900.dp.value, 900.dp.value),
                    style = Stroke(width = 40f, cap = StrokeCap.Round)
                )

                drawContext.canvas.nativeCanvas.drawText(textState.value.toString(), center.x, 200f, scoreTextPaint)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = {
                    textState.value = 30
                }) {
                Text(
                    text = "30"
                )
            }
            OutlinedButton(
                onClick = {
                    textState.value = 60
                }) {
                Text(
                    text = "60"
                )
            }
            OutlinedButton(
                onClick = {
                    textState.value = 120
                }) {
                Text(
                    text = "120"
                )
            }
            OutlinedButton(
                onClick = {
                    textState.value = 180
                }) {
                Text(
                    text = "180"
                )
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
