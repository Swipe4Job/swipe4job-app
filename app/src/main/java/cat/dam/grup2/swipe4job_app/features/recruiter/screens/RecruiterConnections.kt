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
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.recruiter.components.ConnectionsView
import cat.dam.grup2.swipe4job_app.features.recruiter.models.RecruiterConnection


var candidateToView by mutableStateOf<RecruiterConnection?>(null)

fun generateRecruiterConnectionsFakeData(): List<RecruiterConnection> {
    val candidateNames = listOf(
        "Janira",
        "Arià",
        "Alejandro",
        "Edgar"
    )

    val candidateLastnames = listOf(
        "Huesca",
        "Casellas",
        "Marin",
        "Medina"
    )

    val candidatePhones = listOf(
        "611111111",
        "622222222",
        "633333333",
        "644444444"
    )

    val candidateEmails = listOf(
        "candidate1@gmail.com",
        "candidate2@gmail.com",
        "candidate3@gmail.com",
        "candidate4@gmail.com"
    )

    val jobOfferTitles = listOf(
        "Software Engineer",
        "Data Analyst",
        "UX/UI Designer",
        "Product Manager",
    )

    val connectionsList = mutableListOf<RecruiterConnection>()
    repeat(4) {
        val candidateName = candidateNames.random()
        val candidateLastname = candidateLastnames.random()
        val candidatePhone = candidatePhones.random()
        val candidateEmail = candidateEmails.random()
        val jobOfferTitle = jobOfferTitles.random()
        val connectionDate = generateRandomDate()

        connectionsList.add(
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
    return connectionsList
}

val connectionsList = generateRecruiterConnectionsFakeData()
@Composable
fun RecruiterConnections(navController: NavController) {
    var selected by remember { mutableStateOf(BottomNavigationItem.CONNECTIONS) }

    Scaffold(
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
            ConnectionsView(
                connectionsList = connectionsList,
                onContactClick = { candidate ->
                    candidateToView = candidate
                    navController.navigate("candidateContact")
                }
            )
        }
    }
}
