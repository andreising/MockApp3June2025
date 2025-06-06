package com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age

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
import kotlin.collections.listOf

@Composable
fun SexAndAgeChart(userList: State<List<User>>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.sex_and_age),
            style = MaterialTheme.typography.titleMedium
        )
        PeriodSelector(
            listOf(R.string.today, R.string.week, R.string.month, R.string.all_time),
            R.string.all_time
        ) {}
        VisitorsMainInfo(userList.value)
    }
}
