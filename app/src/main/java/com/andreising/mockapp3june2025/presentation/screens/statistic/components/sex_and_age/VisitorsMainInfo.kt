package com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.domain.entity.Sex
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.presentation.utils.circle_chart.CircleSexAndAgeChart

@Composable
fun VisitorsMainInfo(users: List<User>) {
    val maleColor = MaterialTheme.colorScheme.primary
    val femaleColor = MaterialTheme.colorScheme.secondary
    val maleCount = users.count { it.sex == Sex.MALE }
    val femaleCount = users.count { it.sex == Sex.FEMALE }
    val total = maleCount + femaleCount
    if (total == 0) return

    val malePercent = maleCount.toFloat() / total
    val femalePercent = femaleCount.toFloat() / total

    val ageGroups = listOf(
        "18-21" to (18..21),
        "22-25" to (22..25),
        "26-30" to (26..30),
        "31-35" to (31..35),
        "36-40" to (36..40),
        "40-50" to (41..50),
        ">50" to (51..Int.MAX_VALUE)
    )

    Card(
        modifier = Modifier.Companion.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.Companion.padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircleSexAndAgeChart(
                malePercent = malePercent,
                femalePercent = femalePercent,
                maleColor = maleColor,
                femaleColor = femaleColor,
                strokeWidth = 8.dp,
                modifier = Modifier.Companion.height(160.dp)
            )
            SexRatioRow(maleColor, femaleColor, malePercent, femalePercent)
            AgeGroupStats(ageGroups, users, maleColor, femaleColor)
        }
    }
}