package cat.dam.grup2.swipe4job_app.features.recruiter.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.recruiter.state.OfferViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.AddSoftSkillContent
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SoftSkillsList
import cat.dam.grup2.swipe4job_app.shared.composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared.composables.CustomTextFieldMaxChar

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun CompanyPostOfferPage2(navController: NavController) {
    var showAddSkillDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    AddSkillDialog(
        showDialogState = showAddSkillDialog,
        onDismiss = {
            showAddSkillDialog.value = false
        },
        onSaveSkill = {
            OfferViewModel.instance.addSoftSkill(
                SoftSkillsList.fromResourceString(
                    context,
                    it
                )
            )
            showAddSkillDialog.value = false
        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
                //.verticalScroll(enabled = true, state = rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Steps number
                    Text(
                        "2/3",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Description
                    Text(
                        stringResource(id = R.string.description_text),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )

                    // Text field for the description
                    var description = remember {
                        var value = OfferViewModel.instance.description
                        mutableStateOf(value)
                    }

                    LaunchedEffect(description.value) {
                        val offerViewModel = OfferViewModel.instance
                        offerViewModel.description = description.value
                    }
                    CustomTextFieldMaxChar(
                        descriptionState = description,
                        maxCharacters = 1000,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    Text(
                        stringResource(id = R.string.tipsDescriptionPostOffer_text),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Responsabilities
                    Text(
                        stringResource(id = R.string.responsabilities_text),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Text field for the responsabilities
                    var responsibilities = remember {
                        var value = OfferViewModel.instance.responsibilities
                        mutableStateOf(value)
                    }

                    LaunchedEffect(responsibilities.value) {
                        val offerViewModel = OfferViewModel.instance
                        offerViewModel.responsibilities = responsibilities.value
                    }

                    CustomTextFieldMaxChar(
                        descriptionState = responsibilities,
                        maxCharacters = 1500,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Requirements
                    Text(
                        stringResource(id = R.string.requirements_text),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Text field for the requirements
                    var requirements = remember {
                        var value = OfferViewModel.instance.requirements
                        mutableStateOf(value)
                    }
                    LaunchedEffect(requirements.value) {
                        val offerViewModel = OfferViewModel.instance
                        offerViewModel.requirements = requirements.value
                    }

                    CustomTextFieldMaxChar(
                        descriptionState = requirements,
                        maxCharacters = 1500,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Skills
                    Text(
                        stringResource(id = R.string.softSkills_text),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    FlowRow {
                        OfferViewModel.instance.skills.forEachIndexed { index, skill ->
                            SuggestionChip(
                                modifier = Modifier.padding(4.dp, 0.dp),
                                onClick = {
                                    OfferViewModel.instance.skills.removeAt(index)
                                },
                                label = { Text(SoftSkillsList.toResourceString(LocalContext.current, skill)) },

                            )
                        }
                    }

                    // Button - Add skills
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = { showAddSkillDialog.value = true },
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    stringResource(id = R.string.addSoftskills_text),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                                )
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(id = R.string.add_icon_description)
                                )
                            }
                        }
                    }

                    // Sections spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Buttons - Previous + Next
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            CustomButton(
                                onClick = {
                                    navController.navigate("companyPostOfferPage1")
                                },
                                text = stringResource(id = R.string.button_previous_text),
                                modifier = Modifier.weight(1f)
                            )

                            CustomButton(
                                onClick = {
                                    val offerViewModel = OfferViewModel.instance

                                    offerViewModel.description = description.value
                                    offerViewModel.responsibilities = responsibilities.value
                                    offerViewModel.requirements = requirements.value

                                    navController.navigate("companyPostOfferPage3")
                                },
                                text = stringResource(id = R.string.button_next_text),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddSkillDialog(
    showDialogState: MutableState<Boolean>,
    onDismiss: () -> Unit,
    onSaveSkill: (skill: String) -> Unit
) {
    if (!showDialogState.value) {
        return
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .padding(16.dp),
        ) {
            Column {
                AddSoftSkillContent(context = LocalContext.current, onValueChange = {
                    onSaveSkill(it)
                })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCompanyPostOfferPage2Preview() {
    AppTheme {
        CompanyPostOfferPage2(rememberNavController())
    }
}