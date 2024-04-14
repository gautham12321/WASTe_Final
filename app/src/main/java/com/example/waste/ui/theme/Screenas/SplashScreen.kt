package com.example.waste.ui.theme.Screenas

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.waste.MainActivity
import com.example.waste.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(modifier: Modifier=Modifier,navController: NavController){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 200,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1500L)
        navController.navigate(MainActivity.Destinations.LOGIN.name)
    }
    Image(painter = painterResource(id = R.drawable.startup), contentDescription =null, modifier = Modifier.fillMaxSize()
, contentScale = ContentScale.Crop

    )

}