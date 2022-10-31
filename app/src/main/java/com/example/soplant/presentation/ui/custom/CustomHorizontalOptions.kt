package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.theme.TestGreen
import com.example.soplant.presentation.theme.montserrat
import com.example.soplant.presentation.ui.extensions.advancedShadow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomHorizontalOptions(
    image: Int,
    title: String,
    values: List<String>,
    selectedIndex: Int,
    onValueSelected: (Int) -> Unit
) {
    val itemsPadding = 21
    val imageSize = 20
    val cellSize = 58
    val verticalPadding = 10
    val selectCardHeight = cellSize - (verticalPadding * 2)

    val selectCardWidth by animateDpAsState(targetValue = ((selectedIndex + 1) * (imageSize + (itemsPadding))).dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(cellSize.dp)
    ) {
        Card(
            modifier = Modifier
                .advancedShadow(
                    color = Grey,
                    alpha = 0.1f,
                    cornersRadius = 0.dp,
                    shadowBlurRadius = 20.dp
                ),
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(19.dp, verticalPadding.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontFamily = montserrat,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = values[selectedIndex],
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(end = 9.dp)
                )
                Box(
                    modifier = Modifier
                        .width(((imageSize * values.size) + (itemsPadding * (values.size - 1)) + (itemsPadding)).dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(
                                width = ((imageSize + itemsPadding) * values.size).dp,
                                height = selectCardHeight.dp
                            ),
                        shape = RoundedCornerShape((selectCardHeight / 2).dp),
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp,
                        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
                    ) {

                    }
                    Card(
                        modifier = Modifier
                            .size(
                                width = selectCardWidth,
                                height = selectCardHeight.dp
                            ),
                        shape = RoundedCornerShape((selectCardHeight / 2).dp),
                        backgroundColor = TestGreen,
                        elevation = 0.dp,
                        border = BorderStroke((1).dp, MaterialTheme.colors.primary)
                    ) {

                    }
                    Row(
                        modifier = Modifier
                            .height((cellSize - (verticalPadding * 2)).dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        values.forEachIndexed { i, _ ->
                            Card(
                                modifier = Modifier.size(
                                    height = selectCardHeight.dp,
                                    width = (imageSize + itemsPadding).dp
                                ),
                                shape = RoundedCornerShape(((imageSize + itemsPadding) / 2).dp),
                                elevation = 0.dp,
                                backgroundColor = Color.Transparent,
                                onClick = {
                                    onValueSelected(i)
                                }
                            ) {
                                val imageTintColor by animateColorAsState(targetValue = if (i > selectedIndex) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary)
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .size(imageSize.dp),
                                        painter = painterResource(id = image),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(imageTintColor)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomHorizontalOptionsPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CustomHorizontalOptions(
                image = R.drawable.icon_bank,
                title = "Light level",
                values = listOf("Low", "Medium", "High"),
                selectedIndex = 1
            ) {}
        }
    }
}