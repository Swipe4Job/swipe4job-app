package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyPostOfferPage3() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // Steps number
            Text(
                "3/3",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.End)
            )

            // Title - Salary range
            Text(
                "Salary range (optional)",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = "< 12.000 €",
                onValueChange = { /* Acción del recuadro de texto de Workplace type */ },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            // Title - Working hours
            Text(
                "Working hours (optional)",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            // Text field for the working hours
            var workingHours by remember { mutableStateOf("") }
            BasicTextField(
                value = workingHours,
                onValueChange = { workingHours = it },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(6.dp)
                    .height(60.dp)
            )

            // Character counter
            val characterCounter =
                "${workingHours.length}/500"
            Text(
                characterCounter,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.primary
            )

            // Title - Department organization and relationships
            Text(
                "Department organisation and relationships (optional)",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Text field for the department organization and relationships
            var departmentOrganisation by remember { mutableStateOf("") }
            BasicTextField(
                value = departmentOrganisation,
                onValueChange = { departmentOrganisation = it },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(6.dp)
                    .height(120.dp)
            )

            // Character counter
            val characterCounter2 =
                "${departmentOrganisation.length}/2.000"
            Text(
                characterCounter2,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.primary
            )

            // Sections spacer
            Spacer(modifier = Modifier.height(12.dp))
        }
        // Buttons - Previous + Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { /* Acción del primer botón */ }) {
                Text("Previous")
            }

            Button(onClick = { /* Acción del segundo botón */ }) {
                Text("Finish")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCompanyPostOfferPage3Preview() {
    AppTheme {
        CompanyPostOfferPage3()
    }
}
