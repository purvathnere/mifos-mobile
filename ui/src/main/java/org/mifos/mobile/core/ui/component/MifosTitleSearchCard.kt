package org.mifos.mobile.core.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MifosTitleSearchCard(
    searchQuery: (String) -> Unit,
    titleResourceId: Int,
    onSearchDismiss: () -> Unit
) {
    var isSearching by rememberSaveable { mutableStateOf(false) }
    var searchedQuery by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue("")
        )
    }

    if (!isSearching) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = titleResourceId),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier.weight(1f),
                maxLines = 1
            )

            IconButton(onClick = { isSearching = true }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                )
            }
        }
    } else {
        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MifosSearchTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchedQuery,
                onValueChange = {
                    searchedQuery = it
                    searchQuery(it.text)
                },
                onSearchDismiss = {
                    searchedQuery = TextFieldValue("")
                    isSearching = false
                    onSearchDismiss.invoke()
                },
            )
        }
    }
}
