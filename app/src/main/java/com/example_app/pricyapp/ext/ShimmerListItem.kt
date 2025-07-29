package com.example_app.pricyapp.ext

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.Composer
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
import androidx.navigation.compose.ComposeNavigatorDestinationBuilder
import androidx.navigation.compose.rememberNavController

@Composable

fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading){
Row (modifier  = modifier ){
    Box(
        modifier = Modifier
            .size(100.dp)
            .shimmerEffect()

    )
    Spacer(modifier = Modifier.width(16.dp))
    Column(modifier = Modifier.weight(1f)) {
Box(modifier = Modifier
    .fillMaxWidth()
    .height(20.dp)
    . shimmerEffect()

)
Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(20.dp)
            . shimmerEffect()

        )
    }

}

    }else
contentAfterLoading
}


    fun Modifier.shimmerEffect(): Modifier = composed {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition()
        val startOffSetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            )
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFABABAB),
                    Color(0xFF808080),
                    Color(0xFFABABAB)
                ),
                start = Offset(startOffSetX, 0f),
                end = Offset(startOffSetX + size.width.toFloat(), size.height.toFloat())


            )

        )
            .onGloballyPositioned {
                size = it.size
            }
    }


