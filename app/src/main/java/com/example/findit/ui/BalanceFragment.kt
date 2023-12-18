package com.example.findit.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findit.data.BalanceItem

val percs = listOf(
    BalanceItem("Ткань Oxford", 33f),
    BalanceItem("Ткань MEBELW", 70f),
    BalanceItem("Ручка Polar", 62f),
    BalanceItem("Ручка PILOT", 50f),
    BalanceItem("Стикеры BRAUBG", 25f),
    BalanceItem("Одеяло BSleep", 10f),
    BalanceItem("Кровать BAM", 1f),
    BalanceItem("Нить IDA", 17f),
    BalanceItem("Иглы УНИСОН", 99f)
)

@Composable
fun BalanceFragment(
    modifier: Modifier = Modifier
) {
    var items by remember { mutableStateOf(percs) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = "Баланс",
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )

       LazyVerticalGrid(
           columns = GridCells.Fixed(3),
           horizontalArrangement = Arrangement.Center,
           contentPadding = PaddingValues(vertical = 12.dp)
       ) {
           items(items.reversed()) {
               Crossfade(
                   targetState = it,
                   animationSpec = tween(durationMillis = 1000)
               ) { item ->
                   SmallPieChart(
                       item = item,
                       color = Color(
                           (255 * ((100 - item.percs) / 100)).toInt(),
                           (180 * (item.percs / 100)).toInt() + 50,
                           100
                       ),
                       modifier = Modifier
                           .padding(start = 16.dp, bottom = 32.dp)
                           .clickable {
                               items = items.minus(it)
                               items = items.plus(it)
                               it.stared = !it.stared
                           }
                   )
               }
           }
       }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(color = Color.Black)
            Spacer(modifier = Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "add circle", modifier =
                Modifier.size(50.dp))
                Icon(imageVector = Icons.Rounded.Star, contentDescription = "pin circle", modifier =
                Modifier.size(50.dp))
            }
        }
    }
}

@Composable
fun SmallPieChart(item: BalanceItem, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Box {
            Canvas(
                modifier = Modifier
                    .size(75.dp)
            ) {
                val radius = size.minDimension / 2

                drawArc(
                    color = color,
                    startAngle = 0f,
                    sweepAngle = 360 * (item.percs / 100),
                    useCenter = false,
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                    style = Stroke(width = 3.dp.toPx())
                )
                drawArc(
                    color = Color(0.128f, 0.128f, 0.128f, 0.15f),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
            Text(
                text = "${item.percs.toInt()}%",
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color(0f, 0f, 0f)
            )
            if (item.stared) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "null",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                )
            }
        }
        Text(
            text = item.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 4.dp, end = 16.dp),
            color = Color(0f, 0f, 0f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF7FCCBD, showSystemUi = true)
@Composable
fun BalanceFragmentPreview() {
    BalanceFragment(modifier = Modifier
        .padding(horizontal = 32.dp, vertical = 64.dp)
        .fillMaxHeight())
}
