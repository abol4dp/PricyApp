package com.example_app.pricyapp.ext

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

class ShimmerLoader {
    // ثابت‌ها برای تنظیمات شیمر
    private val animationDuration = 1000 // مدت زمان انیمیشن (میلی‌ثانیه)
    private val shimmerOffsetMultiplier = 2f // ضریب جابجایی افکت
    private val iconSize = 100.dp // اندازه آیکون شیمر
    private val textHeight = 20.dp // ارتفاع متن شیمر
    private val spacerWidth = 16.dp // فاصله افقی
    private val spacerHeight = 8.dp // فاصله عمودی

    // وضعیت لودینگ
    var isLoading by mutableStateOf(true)
        private set

    // تابع برای شروع لودینگ
    fun startLoading() {
        isLoading = true
    }

    // تابع برای پایان لودینگ
    fun stopLoading() {
        isLoading = false
    }

    @Composable
    fun ShimmerListItem(
        contentAfterLoading: @Composable () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Box(modifier = modifier) {
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

                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .size(iconSize)
                            .shimmerBackground(size, startOffsetX, onSizeChanged = { newSize ->
                                size = newSize // به‌روزرسانی size
                            })
                    )
                    Spacer(modifier = Modifier.width(spacerWidth))
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(textHeight)
                                .shimmerBackground(size, startOffsetX, onSizeChanged = { newSize ->
                                    size = newSize // به‌روزرسانی size
                                })
                        )
                        Spacer(modifier = Modifier.height(spacerHeight))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(textHeight)
                                .shimmerBackground(size, startOffsetX, onSizeChanged = { newSize ->
                                    size = newSize // به‌روزرسانی size
                                })
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth()) {
                    contentAfterLoading()
                }
            }
        }
    }

    private fun Modifier.shimmerBackground(
        size: IntSize,
        startOffsetX: Float,
        onSizeChanged: (IntSize) -> Unit
    ): Modifier = composed {
        var localSize by remember { mutableStateOf(size) } // متغیر محلی با var
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFABABAB),
                    Color(0xFF808080),
                    Color(0xFFABABAB)
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + localSize.width.toFloat(), localSize.height.toFloat())
            )
        )
            .onGloballyPositioned { layoutCoordinates ->
                val newSize = layoutCoordinates.size
                if (newSize != localSize) {
                    onSizeChanged(newSize) // به‌روزرسانی size از طریق کال‌بک
                    localSize = newSize // به‌روزرسانی متغیر محلی
                }
            }
    }
}