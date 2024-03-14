package cat.dam.grup2.swipe4job_app.shared.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.R

@Composable
fun CustomDateSelectionAlertDialog(
    openDialog: MutableState<Boolean>,
    months: List<String>,
    years: Map<String, String>,
    onAccept: (String) -> Unit
) {
    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = stringResource(id = R.string.selectADate_text)) },
            text = {
                Column {
                    // Dropdown para los meses
                    CustomDropdown(
                        modifier = Modifier.weight(1f),
                        placeholder = stringResource(id = R.string.month_text),
                        items = months,
                        onChange = { month ->
                            selectedMonth = month
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Dropdown para los años
                    CustomDropdown(
                        modifier = Modifier.weight(1f),
                        placeholder = stringResource(id = R.string.year_text),
                        items = years.keys.toList(),
                        onChange = { year ->
                            selectedYear = year
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Botón de aceptar
                    TextButton(onClick = {
                        openDialog.value = false
                        val selectedDate = "${selectedMonth} ${selectedYear}"
                        onAccept(selectedDate)
                    }) {
                        Text(text = stringResource(id = R.string.accept_text))
                    }
                }
            },
            confirmButton = { }
        )
    }
}