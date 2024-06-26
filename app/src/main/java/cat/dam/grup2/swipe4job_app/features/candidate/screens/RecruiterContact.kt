package cat.dam.grup2.swipe4job_app.features.candidate.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidateConnection
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RecruiterContact(navController: NavController, selectedItem: CandidateConnection) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon_description),
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                RecruiterContactInformationDisplay(selectedItem)
            }
        }
    }
    // Manejar el caso cuando no se encuentra el elemento seleccionado
}

@Composable
fun RecruiterContactInformationDisplay(information: CandidateConnection) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Section(
            title = stringResource(id = R.string.label_phoneNumber),
            icon = IconVector.ImageVectorIcon(
                Icons.Default.Phone
            )
        ) {
            Text(
                text = information.recruiterPhone,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Section(
            title = stringResource(id = R.string.label_email),
            icon = IconVector.ImageVectorIcon(
                Icons.Default.Email
            )
        ) {
            Text(
                text = information.recruiterEmail,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}