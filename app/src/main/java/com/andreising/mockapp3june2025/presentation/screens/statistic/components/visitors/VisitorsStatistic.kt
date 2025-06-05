package com.andreising.mockapp3june2025.presentation.screens.statistic.components.visitors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.R
import com.andreising.mockapp3june2025.presentation.theme.MockApp3June2025Theme
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.MonthlyChange
import com.andreising.mockapp3june2025.presentation.utils.simple_graph.SimpleChart
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChart
import com.andreising.mockapp3june2025.presentation.utils.visitor_by_day_graph.VisitorTrendChartModel
import kotlin.random.Random

@Composable
fun VisitorStatistic(visitorsList: List<Int>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.visitors),
            style = MaterialTheme.typography.titleMedium
        )
        VisitorsCard(
            monthlyChange = MonthlyChange.from(visitorsList),
            valueList = visitorsList
        )
        VisitorsChartCard(
            listOf(
                VisitorTrendChartModel("01.08", 42, "1 августа", "42 пользователя"),
                VisitorTrendChartModel("02.08", 76, "2 августа", "76 пользователей"),
                VisitorTrendChartModel("03.08", 33, "3 августа", "33 пользователя"),
                VisitorTrendChartModel("04.08", 89, "4 августа", "89 пользователей"),
                VisitorTrendChartModel("05.08", 57, "5 августа", "57 пользователей"),
                VisitorTrendChartModel("06.08", 24, "6 августа", "24 пользователя"),
                VisitorTrendChartModel("07.08", 65, "7 августа", "65 пользователей")
            )
        )
    }
}

@Composable
fun VisitorsChartCard(visitorsList: List<VisitorTrendChartModel>) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        VisitorTrendChart(
            visitorsList = visitorsList,
            mainColor = MaterialTheme.colorScheme.primary,
            outline = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun VisitorsCard(monthlyChange: MonthlyChange, valueList: List<Int>) {
    Card(
        modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SimpleChart(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                values = valueList,
                color = monthlyChange.color
            )
            Column(modifier = Modifier.weight(2f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = (valueList.lastOrNull() ?: 0).toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    monthlyChange.iconId?.let {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            modifier = Modifier.height(16.dp),
                            painter = painterResource(it),
                            contentDescription = null,
                            tint = monthlyChange.color
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(
                        when (monthlyChange) {
                            is MonthlyChange.Increased -> R.string.visitors_increased
                            is MonthlyChange.Unchanged -> R.string.visitors_unchanged
                            is MonthlyChange.Decreased -> R.string.visitors_decreased
                        }
                    ), style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun VisitorsTest() {
    MockApp3June2025Theme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            VisitorStatistic(List(6) { Random.nextInt(10000) })
        }
    }
}