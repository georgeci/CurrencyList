package com.georgeci.purevm.sample.presentation.feature.details

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgeci.purevm.sample.domain.entity.CurrencyExchangeRate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsComponent(
    viewModel: DetailsViewModel,
) {
    Log.d("QOQ", "1")
    val state = viewModel.state3
    Log.d("QOQ", "2")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Log.d("QOQ", "20")
            TopAppBar(title = {
                Log.d("QOQ", "21")
                Text("Rates: ${viewModel.state2.value}")
                Log.d("QOQ", "22")
            })
        },
    ) { innerPadding ->
        val state = viewModel.state3

        Log.d("QOQ", "3")
        Column {
            Log.d("QOQ", "31")
            ff(
                modifier = Modifier.padding(innerPadding),
                click = viewModel::click,
                value = viewModel.state2,
            )
            Button(
                onClick = {
                    Log.d("QOQ", "5")
                    viewModel.click2()
                }) {
                Log.d("QOQ", "50")
                Text(text = "click2 $")
            }
        }
//        when (val value = state.value) {
//            is DetailsState.Loaded -> {
//                val items = value.items
//                ListItem(
//                    modifier = Modifier.padding(innerPadding),
//                    items,
//                )
//            }
//
//            is DetailsState.Error -> {
//                ListError(
//                    modifier = Modifier.padding(innerPadding),
//                )
//            }
//
//            is DetailsState.Loading -> {
//                ListLoading(
//                    modifier = Modifier.padding(innerPadding),
//                )
//            }
//        }
    }
}

@Composable
fun ff(
    modifier: Modifier = Modifier,
    click: () -> Unit,
    value: Ps,
) {
    Log.d("QOQ", "FF")
    Log.d("QOQ", "FF: ${click.hashCode()}")
    Column(
        modifier = modifier,
    ) {
        Button(onClick = click) {
            Log.d("QOQ", "40")
//            value.value
            Text(text = "Clicks ${value.value}")
        }
    }
}

@Composable
fun ListLoading(
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = "Loading...")
}

@Composable
fun ListError(
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = "Error...")
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    items: List<CurrencyExchangeRate>,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items.size) { item ->
            ListItemRow(
                item = items[item],
            )
        }
    }
}

@Composable
fun ListItemRow(
    item: CurrencyExchangeRate,
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = item.name
        )
    }


}

@Preview
@Composable
fun ListItemRowPreview() {
//    ListItemRow(
//        item = CurrencyItem(
//            "USD", "US Dollar",
//        ),
//        onItemClick = {}
//    )
}

@Preview
@Composable
fun ListItemPreview() {
//    ListItem(
//        items = listOf(
//            CurrencyItem(
//                id = "USD",
//                name = "US Dollar"
//            ),
//            CurrencyItem(
//                id = "EUR",
//                name = "Euro"
//            ),
//        ),
//        onItemClick = {},
//    )
}

@Preview
@Composable
fun ListLoadingPreview() {
    ListLoading()
}

@Preview
@Composable
fun ListErrorPreview() {
    ListError()
}

