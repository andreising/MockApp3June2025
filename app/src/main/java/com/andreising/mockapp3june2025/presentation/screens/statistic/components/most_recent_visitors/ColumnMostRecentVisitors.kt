package com.andreising.mockapp3june2025.presentation.screens.statistic.components.most_recent_visitors

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.andreising.mockapp3june2025.domain.entity.User

@Composable
fun ColumnMostRecentVisitors(userList: List<User>) {
    Card(
        modifier = Modifier.Companion.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        userList.take(3).forEach { user ->
            UserItem(user)
        }
    }
}