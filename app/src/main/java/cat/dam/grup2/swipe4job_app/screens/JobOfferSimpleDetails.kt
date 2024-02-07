package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.composables.MatchButtons
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme
import cat.dam.grup2.swipe4job_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobOfferSimpleDetails() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextButton(
                        onClick = {
                            // Lógica al clickar
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .padding(end = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_moreDetails)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Lógica al hacer clic en el icono de la campana
                    }) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = stringResource(id = R.string.notifications_icon_description)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
//        bottomBar = {
//            BottomAppBar(
//                modifier = Modifier.fillMaxWidth(),
//                containerColor = MaterialTheme.colorScheme.primaryContainer
//            ) {
//                BottomNavigation(
//                    icon = {
//                        Icon(Icons.Default.Home, contentDescription = "Home")
//                    },
//                    selected = false,
//                    onClick = {
//                        // Lógica al hacer clic en la opción de inicio
//                    }
//                )
//                BottomNavigationItem(
//                    icon = {
//                        Icon(Icons.Default.History, contentDescription = "History")
//                    },
//                    selected = false,
//                    onClick = {
//                        // Lógica al hacer clic en la opción de historial
//                    }
//                )
//                BottomNavigationItem(
//                    icon = {
//                        Icon(Icons.Default.Settings, contentDescription = "Settings")
//                    },
//                    selected = false,
//                    onClick = {
//                        // Lógica al hacer clic en la opción de ajustes
//                    }
//                )
//            }
//        }
//        https://medium.com/@chiragthummar16/jetpack-compose-bottom-navigation-with-scaffold-material3-717e28ccc811
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
        ) {
            JobOfferSimpleDetails()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                MatchButtons()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ColumnScope.JobOfferSimpleDetails() {
    val skills = listOf("Kotlin", "Android Development", "Web Development")
    val chipItems = skills.map { ChipItem(label = it, icon = Icons.Default.Done) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(
                text = "Backend developer",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.location_icon_description),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Barcelona",
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Suggestion Chips
            FlowRow {
                chipItems.forEach { chipItem ->
                    SuggestionChip(
                        onClick = { /* Add your click action here */ },
                        label = {
                            Text(
                                chipItem.label,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        border = SuggestionChipDefaults.suggestionChipBorder(
                            borderWidth = 2.dp,
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
            Text(
                text = "Wikiloc (optional field)",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JobOfferSimpleDetailsPreview() {
    AppTheme {
        JobOfferSimpleDetails()
    }
}