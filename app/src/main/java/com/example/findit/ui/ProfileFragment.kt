package com.example.findit.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.findit.R
import com.example.findit.data.Item

@Composable
fun ProfileFragment(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val favItems = listOf(Item(
        R.drawable.podyska1,
        "Подушка 70х70",
        "Рутекс"
    ), Item(
        R.drawable.podyska1,
        "Подушка 70х70",
        "Рутекс"
    ))

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat_camera),
                contentDescription = "profile_photo",
                modifier = Modifier
                    .size(150.dp)
                    .clip(AbsoluteRoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Дмитрий",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Ваш рейтинг: 4.98", textAlign = TextAlign.Center)
            }
        }

        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp)
        ) {
            Text(text = "Статус: онлайн", fontWeight = FontWeight.Bold)
            Text(text = "Специализация: текстиль", fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(18.dp))

        Divider(color = Color.Black)

        NestedListWithIcon(
            neastedListName = "Активные сделки",
            listOfItems = favItems,
            iconOfList = {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "active_deals",
                    modifier = Modifier.size(28.dp)
                )
            }
        )

        Divider(color = Color.Black)

        NestedListWithIcon(
            neastedListName = "Завершенные сделки",
            listOfItems = favItems,
            iconOfList = {
                Icon(
                    painter = painterResource(id = R.drawable.tick_in_circle),
                    contentDescription = "done_deals",
                    modifier = Modifier.size(28.dp)
                )
            }
        )

        Divider(color = Color.Black)

        NestedList(
            neastedListName = "Статистика",
            listOfItems = listOf("Оборот", "Баланс"),
            iconOfList = {
                Icon(
                    painter = painterResource(id = R.drawable.statistic),
                    contentDescription = "statistic",
                    modifier = Modifier.size(28.dp)
                )
            },
            toBalance = { navController.navigate("balance") },
            toFlow = { navController.navigate("moneyFlow") }
        )

        Divider(color = Color.Black)

        NestedListWithIcon(
            neastedListName = "Избранное",
            listOfItems = favItems,
            iconOfList = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite",
                    modifier = Modifier.size(28.dp)
                )
            }
        )

        Divider(color = Color.Black)
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "settings",
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Настройки",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun NestedListWithIcon(
    modifier: Modifier = Modifier,
    neastedListName: String,
    listOfItems: List<Item>,
    iconOfList: @Composable (BoxScope.() -> Unit)
) {
    var openList by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                openList = !openList
            }
        ) {
            Box(content = iconOfList)

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = neastedListName,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        AnimatedVisibility(visible = openList) {
            LazyColumn(contentPadding = PaddingValues(horizontal = 30.dp)) {
                items(listOfItems) { item ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FullItem(
                            item = item
                        )
                    }
                }
            }
        }


    }
}

@Composable
fun NestedList(
    modifier: Modifier = Modifier,
    neastedListName: String,
    listOfItems: List<String>,
    iconOfList: @Composable (BoxScope.() -> Unit),
    toFlow: () -> Unit,
    toBalance: () -> Unit
) {
    var openList by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                openList = !openList
            }
        ) {
            Box(content = iconOfList)

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = neastedListName,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }

        AnimatedVisibility(visible = openList) {
            LazyColumn(contentPadding = PaddingValues(horizontal = 46.dp)) {
                items(listOfItems) { item ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Dot(size = 6.dp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item,
                            modifier = Modifier.clickable(
                                onClick =
                                when (item) {
                                    "Оборот" -> toFlow
                                    "Баланс" -> toBalance
                                    else -> toFlow
                                }
                            ),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }


    }
}

@Composable
fun Dot(
    size: Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color = Color.Black, shape = CircleShape)
    )
}

@Composable
fun FullItem(
    modifier: Modifier = Modifier,
    item: Item
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Color(41, 133, 115, 120))
    ) {
        Row(
            modifier = Modifier.width(250.dp)
        ) {
            Image(
                painter = painterResource(id = item.iconId),
                contentDescription = "null",
                modifier = Modifier
                    .width(50.dp)
                    .clip(AbsoluteRoundedCornerShape(80))
                    .padding(6.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.company
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    FullItem( item =
        Item(
            R.drawable.podyska1,
            "Подушка 70х70",
            "Рутекс"
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF7FCCBD)
@Composable
fun ProfileFragmentPreview() {
    ProfileFragment(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 24.dp)
    )
}