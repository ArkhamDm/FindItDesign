@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.findit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findit.R
import com.example.findit.ui.theme.FindItTheme

@Composable
fun CatalogFragment(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Find it?",
            fontSize = 68.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        SearchTextField()

        Spacer(modifier = Modifier.height(32.dp))

        ListCategory()
    }
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
        },
        placeholder = {
            Text(text = "Поиск")
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray),
        trailingIcon = {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "filter")
        }
    )
}

@Composable
fun ListCategory(
    modifier: Modifier = Modifier
) {
    val categories = mapOf(
        R.drawable.building to "Стройматериалы",
        R.drawable.sewing to "Швейное дело",
        R.drawable.office to "Офис",
        R.drawable.electronics to "Электроника",
        R.drawable.ready_made_food to "Готовая еда",
        R.drawable.ingredients to "Ингредиенты"
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(categories.keys.toList()) {
            IconTextCategory(
                imageId = it,
                textOfCategory = categories[it]!!,
                modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}

@Composable
fun IconTextCategory(
    modifier: Modifier = Modifier,
    imageId: Int,
    textOfCategory: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = textOfCategory,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(AbsoluteRoundedCornerShape(50))
                .size(115.dp)
        )
        Text(
            text = textOfCategory
        )
    }
}

@Preview(showSystemUi = false, showBackground = true, backgroundColor = 0xFF7FCCBD)
@Composable
fun CatalogPreview() {
    FindItTheme {
        CatalogFragment()
    }
}