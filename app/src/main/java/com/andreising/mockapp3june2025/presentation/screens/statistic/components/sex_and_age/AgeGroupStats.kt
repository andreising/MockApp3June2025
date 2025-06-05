package com.andreising.mockapp3june2025.presentation.screens.statistic.components.sex_and_age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andreising.mockapp3june2025.domain.entity.Sex
import com.andreising.mockapp3june2025.domain.entity.User

@Composable
fun AgeGroupStats(
    ageGroups: List<Pair<String, IntRange>>,
    users: List<User>,
    maleColor: Color,
    femaleColor: Color
) {
    ageGroups.forEach { (label, range) ->
        val groupUsers = users.filter { it.age in range }
        val total = users.size
        val maleCount = groupUsers.count { it.sex == Sex.MALE }
        val femaleCount = groupUsers.count { it.sex == Sex.FEMALE }

        Row(
            modifier = Modifier.Companion.fillMaxWidth(),
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(
                text = label,
                color = Color.Companion.Black,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Companion.Start,
                modifier = Modifier.Companion
                    .weight(0.3f)
                    .padding(start = 16.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.Companion.weight(0.7f)
            ) {
                GenderBar(maleCount, total, maleColor)
                GenderBar(femaleCount, total, femaleColor)
            }
        }
    }
}