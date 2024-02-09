package com.shaiful.pokedex.screens.pokemon_types.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shaiful.pokedex.R
import com.shaiful.pokedex.navigation.RouteNames
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonType
import java.util.Locale

@Composable
fun TypeListEntryCompo(
    navController: NavController,
    modifier: Modifier = Modifier,
    type: PokemonType,
    index: Int,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .width(100.dp)
            .background(color = Color.White)
            .clickable {
                navController.navigate(
                    "${RouteNames.pokemonTypeDetailsScreen}/${type.type}"
                )
            }
    ) {
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = "Pokeball",
                modifier = Modifier.size(56.dp)
                    .weight(2f)
            )
            Text(
                text = type.type.replaceFirstChar { it.uppercaseChar() },
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}