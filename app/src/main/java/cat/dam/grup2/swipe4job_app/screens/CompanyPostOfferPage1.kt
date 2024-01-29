package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyPostOfferPage1() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(enabled = true, state = rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // Steps number
            Text(
                "1/3",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.End)
            )

            // Section title
            Text(
                "Post a job offer",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Title - Job title
            Text(
                "Job title",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción del recuadro de texto de Position */ },
                label = { Text("Enter the position") },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            // Title - Company
            Text(
                "Company",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción del recuadro de texto de Company */ },
                label = { Text("Enter the company name") },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            // Title - Job type
            Text(
                "Job type",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = "On-site work",
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

            // Title - Location
            Text(
                "Location",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = { /* Acción del recuadro de texto de Location */ },
                label = { Text("Enter the location") },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            // Title - Contract type
            Text(
                "Contract type",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = "Indefinite",
                onValueChange = { /* Acción del recuadro de texto de Workplace type */ },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            // Title - Working day type
            Text(
                "Working day type",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            OutlinedTextField(
                value = "Full time",
                onValueChange = { /* Acción del recuadro de texto de Job type */ },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            // Sections spacer
            Spacer(modifier = Modifier.height(12.dp))
        }
        Button(
            onClick = { /* Handle the login action here */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCompanyPostOfferPage1Preview() {
    AppTheme {
        CompanyPostOfferPage1()
    }
}
