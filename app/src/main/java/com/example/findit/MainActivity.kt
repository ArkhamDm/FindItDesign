package com.example.findit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.findit.ui.BalanceFragment
import com.example.findit.ui.CatalogFragment
import com.example.findit.ui.LoginFragment
import com.example.findit.ui.MoneyFlowFragment
import com.example.findit.ui.theme.FindItTheme
import com.example.findit.ui.ProfileFragment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nav = rememberNavController()
                    NavHost(navController = nav, startDestination = "login") {
                        composable("login") {
                            LoginFragment(
                                modifier = Modifier.padding(32.dp),
                                navController = nav
                            )
                        }
                        composable("catalog") {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                        .fillMaxWidth()
                                    ,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    ProfileIcon(navController = nav)
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = "list",
                                        modifier = Modifier.size(45.dp)
                                    )
                                }
                                CatalogFragment(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp, vertical = 32.dp)
                                )
                            }
                        }
                        composable("profile") {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.End
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    CloseIcon(navController = nav)
                                }
                                ProfileFragment(
                                    modifier = Modifier
                                        .padding(start = 32.dp, end = 32.dp, bottom = 12.dp),
                                    navController = nav
                                )
                            }
                        }
                        composable("balance") {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    CloseIcon(navController = nav)
                                }
                                BalanceFragment(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                                )
                            }
                        }
                        composable("moneyFlow") {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    CloseIcon(navController = nav)
                                }
                                MoneyFlowFragment(modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ProfileIcon(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.cat_camera),
        contentDescription = "profile",
        modifier = Modifier
            .clickable {
                navController.navigate("profile")
            }
            .size(45.dp)
            .clip(AbsoluteRoundedCornerShape(50))
    )
}

@Composable
fun CloseIcon(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = null,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {
                navController.popBackStack()
            }
            .size(30.dp)
    )
}
