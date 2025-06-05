package com.andreising.mockapp3june2025.presentation.screens.statistic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.domain.entity.Sex
import com.andreising.mockapp3june2025.domain.entity.User
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.most_recent_visitors.MostRecentVisitors
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age.SexAndAgeChart
import com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors.VisitorStatistic
import kotlin.random.Random

@Composable
fun StatisticScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.statistic),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item { VisitorStatistic(List(6) { Random.nextInt(10000) }) }
        item { MostRecentVisitors() }
        item { SexAndAgeChart(listOf(
                User(
                    id = 1,
                    sex = Sex.MALE,
                    username = "Alex92",
                    isOnline = true,
                    age = 28,
                    avatarUrl = "https://example.com/avatars/alex92.jpg"
                ),
            User(
                id = 2,
                sex = Sex.FEMALE,
                username = "JuliaS",
                isOnline = false,
                age = 34,
                avatarUrl = "https://example.com/avatars/julias.jpg"
            ),
            User(
                id = 3,
                sex = Sex.MALE,
                username = "Mike_T",
                isOnline = true,
                age = 22,
                avatarUrl = "https://example.com/avatars/miket.jpg"
            ),
            User(
                id = 4,
                sex = Sex.FEMALE,
                username = "AnnaK",
                isOnline = true,
                age = 45,
                avatarUrl = "https://example.com/avatars/annak.jpg"
            ),
            User(
                id = 5,
                sex = Sex.MALE,
                username = "Sergey_L",
                isOnline = false,
                age = 38,
                avatarUrl = "https://example.com/avatars/sergeyl.jpg"
            ),
            User(
                id = 6,
                sex = Sex.FEMALE,
                username = "Olga_V",
                isOnline = true,
                age = 25,
                avatarUrl = "https://example.com/avatars/olga_v.jpg"
            ),
            User(
                id = 7,
                sex = Sex.MALE,
                username = "Dmitry89",
                isOnline = false,
                age = 33,
                avatarUrl = "https://example.com/avatars/dmitry89.jpg"
            ),
            User(
                id = 8,
                sex = Sex.FEMALE,
                username = "NatalieP",
                isOnline = true,
                age = 19,
                avatarUrl = "https://example.com/avatars/nataliep.jpg"
            ),
            User(
                id = 9,
                sex = Sex.MALE,
                username = "Ivan_I",
                isOnline = false,
                age = 52,
                avatarUrl = "https://example.com/avatars/ivani.jpg"
            ),
            User(
                id = 10,
                sex = Sex.FEMALE,
                username = "Sofia_G",
                isOnline = true,
                age = 27,
                avatarUrl = "https://example.com/avatars/sofiag.jpg"
            )
            )) }
    }
}