package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.theme.montserrat
import com.example.soplant.presentation.ui.extensions.advancedShadow

data class CustomHorizontalPickerModel(val title: String, val key: String)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomHorizontalPicker(
    modifier: Modifier = Modifier,
    title: String,
    values: List<CustomHorizontalPickerModel>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = modifier) {
        Text(
            fontFamily = montserrat,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = MaterialTheme.colors.primary,
            text = title,
            modifier = Modifier.padding(start = 7.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .advancedShadow(
                    color = Grey,
                    alpha = 0.1f,
                    cornersRadius = 0.dp,
                    shadowBlurRadius = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            values.forEachIndexed { i, value ->
                val isFirst = i == 0
                val isLast = i == values.size - 1
                val isSelected = selectedValue == value.key
                Card(
                    shape = RoundedCornerShape(
                        if (isFirst) 15.dp else 0.dp,
                        if (isLast) 15.dp else 0.dp,
                        if (isLast) 15.dp else 0.dp,
                        if (isFirst) 15.dp else 0.dp
                    ),
                    elevation = 0.dp,
                    backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    onClick = {
                        onValueChange(value.key)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = value.title,
                            style = if (values.size < 3) MaterialTheme.typography.body1 else MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center,
                            color = if (isSelected) MaterialTheme.colors.background else MaterialTheme.colors.primary
                        )
                    }
                }
                if (!isLast) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .width(1.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Custom horizontal picker")
@Composable
fun CustomHorizontalPickerPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var selectedValue by remember {
                mutableStateOf("")
            }
            CustomHorizontalPicker(
                modifier = Modifier.padding(horizontal = 10.dp),
                title = "Title",
                values = listOf(
                    CustomHorizontalPickerModel("Test 1", "key_1"),
                    CustomHorizontalPickerModel("Test2", "key_2"),
                    CustomHorizontalPickerModel("Test3", "key_3")
                ),
                selectedValue = selectedValue,
                onValueChange = {
                    selectedValue = it
                }
            )
        }
    }
}