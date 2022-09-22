package com.example.soplant.presentation.ui.add_fund

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.components.TopBarComponent
import com.example.soplant.presentation.ui.custom.greenButtonColors
import com.example.soplant.presentation.ui.custom.whiteButtonColors
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun ComposeAddFundScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarComponent(navController = navController, title = "Add funds")
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 20.dp)
                .weight(1f)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .advancedShadow(
                        color = Black,
                        alpha = 0.08f,
                        cornersRadius = 15.dp,
                        shadowBlurRadius = 20.dp
                    ),
                elevation = 0.dp,
                shape = MaterialTheme.shapes.large
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 32.dp)
                        .height(58.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(58.dp)
                            .background(color = MaterialTheme.colors.primary.copy(alpha = 0.19f)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "€",
                            style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                            color = MaterialTheme.colors.primary
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "12.34",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.h1.copy(fontSize = 25.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "1",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "2",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "3",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "4",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "5",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "6",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "7",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "8",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "9",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = ".",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "0",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {},
                            elevation = zeroButtonElevation(),
                            colors = whiteButtonColors(),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .fillMaxSize()
                                .advancedShadow(
                                    color = Black,
                                    alpha = 0.08f,
                                    cornersRadius = 15.dp,
                                    shadowBlurRadius = 20.dp
                                )
                        ) {
                            Text(
                                text = "<-",
                                style = MaterialTheme.typography.h1.copy(fontSize = 25.sp),
                                color = Grey
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp, bottom = 20.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MaterialTheme.shapes.large,
                colors = greenButtonColors(),
                elevation = zeroButtonElevation(),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Continue", style = MaterialTheme.typography.button)
            }
        }
    }
}