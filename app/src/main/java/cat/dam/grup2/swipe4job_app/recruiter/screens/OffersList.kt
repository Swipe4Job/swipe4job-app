package cat.dam.grup2.swipe4job_app.recruiter.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.recruiter.components.BottomNavigationBar
import cat.dam.grup2.swipe4job_app.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.recruiter.components.OffersListView
import cat.dam.grup2.swipe4job_app.recruiter.modelos.JobOfferInformation


var offerList = mutableStateListOf<JobOfferInformation>()
private var itemToDelete by mutableStateOf<JobOfferInformation?>(null)
private var itemToEdit by mutableStateOf<JobOfferInformation?>(null)

@OptIn(ExperimentalComposeUiApi::class)
lateinit var keyboardController: SoftwareKeyboardController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OffersList(navController: NavController) {

    var selected by remember { mutableStateOf(BottomNavigationItem.OFFERS) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    /* TODO */
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_button_description)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                searchClick = { selected = BottomNavigationItem.SEARCH },
                connectionsClick = { selected = BottomNavigationItem.CONNECTIONS },
                offersClick = { selected = BottomNavigationItem.OFFERS },
                notificationsClick = { selected = BottomNavigationItem.NOTIFICATIONS },
                selected = selected,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OffersListView(offerList, onEditClick = { item ->
                itemToEdit = item
            }, onDeleteClick = { item ->
                itemToDelete = item
            })
        }
    }
}