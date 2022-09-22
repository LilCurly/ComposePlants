package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soplant.R
import com.example.soplant.domain.entities.Transaction
import com.example.soplant.presentation.theme.GreenSuccess
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.GreyLight
import com.example.soplant.presentation.ui.extensions.advancedShadow
import com.example.soplant.presentation.utils.ColorFinder
import com.example.soplant.presentation.utils.IconFinder
import com.example.soplant.presentation.utils.StringTransformer
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.material.placeholder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransactionComponent(transaction: Transaction?) {
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .advancedShadow(
                color = Grey,
                alpha = 0.1f,
                cornersRadius = 15.dp,
                shadowBlurRadius = 10.dp
            )
            .placeholder(
                visible = transaction == null,
                highlight = PlaceholderHighlight.shimmer()
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp, vertical = 13.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = IconFinder.findTransactionIcon(transaction?.origin ?: "")),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = StringTransformer.convertTransactionOriginToString(transaction?.origin ?: ""),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = DateFormat.getDateTimeInstance().format(Date(transaction?.createdOn ?: 0 * 1000)),
                    style = MaterialTheme.typography.body2,
                    color = GreyLight
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = StringTransformer.convertTransactionPrice(transaction?.origin ?: "", transaction?.amount ?: ""),
                    style = MaterialTheme.typography.body1,
                    color = ColorFinder.findTransactionColor(transaction?.origin ?: "")
                )
            }
        }
    }
}