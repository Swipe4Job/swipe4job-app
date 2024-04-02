package cat.dam.grup2.swipe4job_app.features.candidate.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddSoftskillViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateProfileViewModel
import cat.dam.grup2.swipe4job_app.shared.composables.CustomFilterableTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSoftSkill(navController: NavController) {
    var candidateProfileViewModel = CandidateProfileViewModel.getInstance()
    val addSoftskillViewModel = AddSoftskillViewModel.instance
    val isEditing = remember {
        addSoftskillViewModel.editingSoftSkills.size > 0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back_icon_description),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = if (!isEditing) stringResource(id = R.string.addSoftskills_text) else stringResource(
                                R.string.editSoftskills_text
                            ),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                        Text(
                            /* TODO: verify that at least one Soft skill has been selected */
                            text = stringResource(id = R.string.save_text),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .clickable {
                                    candidateProfileViewModel.softSkills.clear()
                                    candidateProfileViewModel.softSkills.addAll(
                                        AddSoftskillViewModel.instance.editingSoftSkills
                                    )
                                    // TODO: Save data in database
                                    navController.popBackStack()
                                }
                                .padding(end = 16.dp),
                            color = MaterialTheme.colorScheme.primary
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
                AddSoftSkillContent(context = LocalContext.current) {
                    AddSoftskillViewModel.instance.addSoftSkill(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddSoftSkillContent(context: Context, onValueChange: (String) -> Unit) {
    val resources = context.resources
    val suggestionsArray = resources.getStringArray(R.array.soft_skills_array).sortedArray()

    Column {
        val addSoftSkillViewModel = AddSoftskillViewModel.instance
        FlowRow {
            for (i in 0..addSoftSkillViewModel.editingSoftSkills.size - 1) {
                val item = addSoftSkillViewModel.editingSoftSkills[i]
                SuggestionChip(
                    onClick = { },
                    label = {
                        Text(item)
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                addSoftSkillViewModel.editingSoftSkills.removeAt(
                                    i
                                )
                            }
                        )
                    }, modifier = Modifier.padding(4.dp, 0.dp)
                )
            }
        }
        CustomFilterableTextField(
            suggestions = suggestionsArray,
            onItemSelected = onValueChange
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddSoftSkillPreview() {
    AddSoftSkill(rememberNavController())
}