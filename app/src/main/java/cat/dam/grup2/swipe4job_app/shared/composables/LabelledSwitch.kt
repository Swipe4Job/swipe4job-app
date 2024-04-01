package cat.dam.grup2.swipe4job_app.shared.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabelledSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    label: String,
    onCheckedChange: ((Boolean) -> Unit),
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 16.dp)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = colors,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
