package cat.dam.grup2.swipe4job_app.features.recruiter.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterConnectionsView
import cat.dam.grup2.swipe4job_app.features.recruiter.models.RecruiterConnection
import cat.dam.grup2.swipe4job_app.features.recruiter.state.RecruiterConnectionsViewModel


var candidateToView by mutableStateOf<RecruiterConnection?>(null)

fun generateRecruiterConnectionsFakeData(): List<RecruiterConnection> {
    val candidateNames = listOf(
        "Arià",
        "Alejandro",
        "Edgar"
    )

    val candidateLastnames = listOf(
        "Casellas",
        "Marin",
        "Medina"
    )

    val candidatePhones = listOf(
        "611111111",
        "622222222",
        "633333333"
    )

    val candidateEmails = listOf(
        "candidate1@gmail.com",
        "candidate2@gmail.com",
        "candidate3@gmail.com"
    )

    val jobOfferTitles = listOf(
        "Programador Java",
        "Programador/a .NET junior",
        "Desarrollador/a Front"
    )

    val recruiterConnectionsList = mutableListOf<RecruiterConnection>()
    repeat(3) {
        val candidateName = candidateNames[it]
        val candidateLastname = candidateLastnames[it]
        val candidatePhone = candidatePhones[it]
        val candidateEmail = candidateEmails[it]
        val jobOfferTitle = jobOfferTitles[it]
        val connectionDate = generateRandomDate()

        recruiterConnectionsList.add(
            RecruiterConnection(
                candidateName = candidateName,
                candidateLastName = candidateLastname,
                candidatePhone = candidatePhone,
                candidateEmail = candidateEmail,
                jobOfferTitle = jobOfferTitle,
                connectionDate = connectionDate
            )
        )
    }
    return recruiterConnectionsList
}

val recruiterConnectionsList = RecruiterConnectionsViewModel.obtainInstance().notifications
@Composable
fun RecruiterConnections(navController: NavController) {
    var selected by remember { mutableStateOf(BottomNavigationItem.CONNECTIONS) }

    Scaffold(
        bottomBar = {
            RecruiterBottomNavigationBar(
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
            RecruiterConnectionsView(
                recruiterConnectionsList = recruiterConnectionsList,
                onContactClick = { candidate ->
                    candidateToView = candidate
                    navController.navigate("candidateContact")
                }
            )
        }
    }
}
