package cat.dam.astrodev.navigation_template.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public fun MatchButtons() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LargeFloatingActionButton(onClick = {}, shape = CircleShape, modifier = Modifier.size(80.dp)) {
            Icon(Icons.Filled.Close, "Dislike", modifier = Modifier.size(40.dp))
        }
        LargeFloatingActionButton(onClick = {}, shape = CircleShape, modifier = Modifier.size(80.dp)) {
            Icon(Icons.Filled.Favorite, "Like", modifier = Modifier.size(40.dp))
        }
    }
}