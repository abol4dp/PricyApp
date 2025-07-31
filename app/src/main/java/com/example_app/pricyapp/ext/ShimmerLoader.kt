package com.example_app.pricyapp.ext

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ShimmerLoader {
    private val animationDuration = 1000
    private val shimmerOffsetMultiplier = 2f
    private val iconSize = 100.dp
    private val textHeight = 17.dp
    private val spacerWidth = 16.dp
    private val spacerHeight = 8.dp


    var isLoading by mutableStateOf(true)
        private set


    fun startLoading() {
        isLoading = true
    }


    fun stopLoading() {
        isLoading = false
    }

    @Composable
    fun ShimmerListItem(


        contentAfterLoading: @Composable () -> Unit, modifier: Modifier = Modifier

    ) {


        if (isLoading) {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {


                Box(
                    modifier = modifier
                        .padding(top = 6.dp)
                ) {
                    if (isLoading) {
                        var size by remember { mutableStateOf(IntSize.Zero) }
                        val transition = rememberInfiniteTransition(label = "shimmerTransition")
                        val startOffsetX by transition.animateFloat(
                            initialValue = -shimmerOffsetMultiplier * size.width.toFloat(),
                            targetValue = shimmerOffsetMultiplier * size.width.toFloat(),
                            animationSpec = infiniteRepeatable(
                                animation = TweenSpec(durationMillis = animationDuration),
                                repeatMode = RepeatMode.Restart
                            ),
                            label = "shimmerOffset"
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End


                        ) {
                            Box(
                                modifier = Modifier
                                    .size(iconSize)
                                    .shimmerBackground(
                                        size,
                                        startOffsetX,
                                        onSizeChanged = { newSize ->
                                            size = newSize
                                        })
                            )
                            Spacer(modifier = Modifier.width(spacerWidth))
                            Column(
                                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(textHeight)
                                        .shimmerBackground(
                                            size,
                                            startOffsetX,
                                            onSizeChanged = { newSize ->
                                                size = newSize
                                            })
                                )
                                Spacer(modifier = Modifier.height(spacerHeight))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f)
                                        .height(textHeight)
                                        .shimmerBackground(
                                            size,
                                            startOffsetX,
                                            onSizeChanged = { newSize ->
                                                size = newSize
                                            })
                                )
                            }
                        }
                    }
                }


            }
        } else {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {


                contentAfterLoading()
            }
        }
    }
}

private fun Modifier.shimmerBackground(
    size: IntSize, startOffsetX: Float, onSizeChanged: (IntSize) -> Unit
): Modifier = composed {
    var localSize by remember { mutableStateOf(size) }
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF9BA5C9),
                Color(0xFF3E414D),
                Color(0xFF808FAB)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + localSize.width.toFloat(), localSize.height.toFloat())
        )
    ).onGloballyPositioned { layoutCoordinates ->
        val newSize = layoutCoordinates.size
        if (newSize != localSize) {
            onSizeChanged(newSize)
            localSize = newSize
        }
    }
}
