package cat.dam.grup2.swipe4job_app.shared.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavBarBadge(content: String, modifier: Modifier) {
    Badge(
        content = {
            Text(
                text = content,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 10.sp,
            )
        },
        modifier = modifier
            .padding(4.dp)
            .size(16.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )
}