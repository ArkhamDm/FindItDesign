@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.findit.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.findit.R

@Composable
fun LoginFragment(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(AbsoluteRoundedCornerShape(50))
        )

        Column {
            Text(
                text = "Вход",
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text =  "Email"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedLabelColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text =  "Пароль"
                    )
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "hide/open password")
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedLabelColor = Color.Black
                ),
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Column {
            LoginButton(
                text = "Войти",
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textUnder = "или использовать соц. сети",
                next = { navController.navigate("catalog") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                LoginButton(text = "Vkontakte", color = Color.Blue, textUnder = "Забыли пароль?")
                LoginButton(text = "Telegram", color = Color.Black, textUnder = "Регистрация",
                    textUnderBold = true)
            }
        }

    }
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    textUnder: String,
    textUnderBold: Boolean = false,
    next: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = next,
            shape = ShapeDefaults.ExtraSmall,
            colors = ButtonDefaults.buttonColors(containerColor = color),
            modifier = modifier,
            contentPadding = PaddingValues(start = 36.dp, top = 12.dp, end = 36.dp, bottom = 12.dp)
        ) {
            Text(
                text = text,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = textUnder,
            fontWeight = if (textUnderBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF7FCCBD)
@Composable
fun LoginPreview() {
    LoginFragment(modifier = Modifier
        .fillMaxHeight()
        .padding(32.dp))
}