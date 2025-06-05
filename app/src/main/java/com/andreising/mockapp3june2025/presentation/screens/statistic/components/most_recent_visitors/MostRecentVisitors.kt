package com.andreising.mockapp3june2025.presentation.screens.statistic.components.most_recent_visitors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.domain.entity.User

@Composable
fun MostRecentVisitors(userList: State<List<User>>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.most_recent_visitors),
            style = MaterialTheme.typography.titleMedium
        )
        ColumnMostRecentVisitors(userList.value)
    }
}
