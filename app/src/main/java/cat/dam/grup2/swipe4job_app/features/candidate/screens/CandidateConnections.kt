package cat.dam.grup2.swipe4job_app.features.candidate.screens

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
import cat.dam.grup2.swipe4job_app.features.candidate.components.CandidateConnectionsView
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidateConnection
import cat.dam.grup2.swipe4job_app.features.candidate.components.CandidateBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.candidate.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateConnectionsViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.generateRandomDate


var recruiterToView by mutableStateOf<CandidateConnection?>(null)

fun generateCandidateConnectionsFakeData(): List<CandidateConnection> {
//    val recruiterNames = listOf(
//        "Marc",
//        "Joan",
//        "David",
//        "Úrsula"
//    )
    val recruiterName = "Janira"

//    val recruiterLastnames = listOf(
//        "Pararols",
//        "Coll",
//        "Lozano",
//        "Heredia"
//    )
    val recruiterLastname = "Huesca"

//    val recruiterPhones = listOf(
//        "611111111",
//        "622222222",
//        "633333333",
//        "644444444"
//    )
    val recruiterPhone = "611111111"

//    val recruiterEmails = listOf(
//        "recruiter1@gmail.com",
//        "recruiter2@gmail.com",
//        "recruiter3@gmail.com",
//        "recruiter4@gmail.com"
//    )
    val recruiterEmail = "janira@example.com"

    val jobOfferTitles = listOf(
        "Programador Java",
        "Programador/a .NET junior",
        "Programador Web",
        "Programador/Programadora .NET/Java",
        "Desarrollador/a Front",
    )

    val candidateConnectionsList = mutableListOf<CandidateConnection>()
    repeat(5) {
        val recruiterName = recruiterName
        val recruiterLastname = recruiterLastname
        val recruiterPhone = recruiterPhone
        val recruiterEmail = recruiterEmail
        val jobOfferTitle = jobOfferTitles[it]
        val connectionDate = generateRandomDate()

        candidateConnectionsList.add(
            CandidateConnection(
                recruiterName = recruiterName,
                recruiterLastName = recruiterLastname,
                recruiterPhone = recruiterPhone,
                recruiterEmail = recruiterEmail,
                jobOfferTitle = jobOfferTitle,
                connectionDate = connectionDate
            )
        )
    }
    return candidateConnectionsList
}

val candidateConnectionsList = CandidateConnectionsViewModel.obtainInstance().notifications

@Composable
fun CandidateConnections(navController: NavController) {
    var selected by remember { mutableStateOf(BottomNavigationItem.CONNECTIONS) }

    Scaffold(
        bottomBar = {
            CandidateBottomNavigationBar(
                searchClick = { selected = BottomNavigationItem.SEARCH },
                connectionsClick = { selected = BottomNavigationItem.CONNECTIONS },
                cvClick = { selected = BottomNavigationItem.CV },
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
            CandidateConnectionsView(
                candidateConnectionsList = candidateConnectionsList,
                onContactClick = { recruiter ->
                    recruiterToView = recruiter
                    navController.navigate("recruiterContact")
                }
            )
        }
    }
}