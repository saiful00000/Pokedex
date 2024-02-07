package com.shaiful.pokedex.screens.pokemon_list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shaiful.pokedex.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun MainAppBar(
    navController: NavController
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pikachu))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
//            modifier = Modifier.weight(1f)
        )
        Row (
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "PokeDex",
                    style = TextStyle(
                        color = Color.Magenta,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                )
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "See Types")
                }
            }
        }
    }
}