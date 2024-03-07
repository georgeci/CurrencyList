package com.georgeci.purevm.sample.presentation.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgeci.purevm.sample.domain.entity.CurrencyItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListComponent(
    viewModel: ListViewModel,
    onItemClick: (CurrencyItem) -> Unit,
) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Top App Bar") }) },
    ) { innerPadding ->
        when (val value = state.value) {
            is ListState.Loaded -> {
                val items = value.items
                ListItem(
                    modifier = Modifier.padding(innerPadding),
                    items,
                    onItemClick,
                )
            }

            is ListState.Error -> {
                ListError(
                    modifier = Modifier.padding(innerPadding),
                )
            }

            is ListState.Loading -> {
                ListLoading(
                    modifier = Modifier.padding(innerPadding),
                )
            }
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
    items: List<CurrencyItem>,
    onItemClick: (CurrencyItem) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items.size) { item ->
            ListItemRow(
                item = items[item],
                onItemClick = onItemClick,
            )
        }
    }
}

@Composable
fun ListItemRow(
    item: CurrencyItem,
    onItemClick: (CurrencyItem) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { onItemClick(item) },
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
    ListItemRow(
        item = CurrencyItem(
            "USD", "US Dollar",
        ),
        onItemClick = {}
    )
}

@Preview
@Composable
fun ListItemPreview() {
    ListItem(
        items = listOf(
            CurrencyItem(
                id = "USD",
                name = "US Dollar"
            ),
            CurrencyItem(
                id = "EUR",
                name = "Euro"
            ),
        ),
        onItemClick = {},
    )
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

