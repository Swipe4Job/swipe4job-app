package cat.dam.grup2.swipe4job_app.features.candidate.screens

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.components.CandidateBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.candidate.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidatePreferences
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddExperienceViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddLanguageViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddPreferencesViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddSoftskillViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddStudyViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateProfileViewModel
import cat.dam.grup2.swipe4job_app.features.users.state.UserViewModel
import cat.dam.grup2.swipe4job_app.userApiService
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidateCV(navController: NavController) {
    var selected by remember { mutableStateOf(BottomNavigationItem.CV) }
    val candidateProfileViewModel = CandidateProfileViewModel.getInstance()
    val languagesList = candidateProfileViewModel.languages
    val studiesList = candidateProfileViewModel.studies
    val experiencesList = candidateProfileViewModel.experiences
    val context = LocalContext.current
    val candidatePreferences = candidateProfileViewModel.preferences
    var openEditBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomEditSheetState = rememberModalBottomSheetState()
    val fullName = candidateProfileViewModel.fullName

//    candidateProfileViewModel.fetchRemoteCVData()

    val requestPermissionsLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false


            if (!cameraGranted) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            }
        }


    var permissionRequested by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = permissionRequested) {
        if (!permissionRequested) {
            val cameraPermission =
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            val storagePermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (cameraPermission != PackageManager.PERMISSION_GRANTED || storagePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissionsLauncher.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
            }
            permissionRequested = true
        }
    }

//    val candidate = userApiService.listCandidates()
//    val candidate = CandidateInformation(
//        description = "",
//        studies = listOf(),
//        softskills = listOf(),
//        name = "Paco",
//        lastname = "Garcia",
//        location = "",
//        languages = listOf(),
//        jobExperience = listOf()
//    )

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
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                Header(
                    fullName = fullName.value,
                    editClick = { openEditBottomSheet = !openEditBottomSheet },
                    profileImageUri = CandidateProfileViewModel.getInstance().imageURI.value
                )
                Experience(navController, experiencesList)
                Studies(navController, studiesList)
                SoftSkills(navController, candidateProfileViewModel.softSkills)
                Languages(navController, languagesList)
                Preferences(navController, candidatePreferences)
            }
        }
    }

    if (openEditBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { openEditBottomSheet = false },
            sheetState = bottomEditSheetState,
            content = {
                val context = LocalContext.current
                EditOptions(
                    onTakePhotoClick = {

                    },
                    onChoosePhotoClick = {
                        CandidateProfileViewModel.getInstance().imageURI.value = it
                    }
                )
            }
        )
    }
}


@Composable
fun EditOptions(
    onTakePhotoClick: () -> Unit,
    onChoosePhotoClick: (Uri) -> Unit
) {
    val context = LocalContext.current

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap?
            imageBitmap?.let { bitmap ->
                val uri = saveImageToGallery(context, bitmap)
                uri?.let { onChoosePhotoClick(it) }
            }
        }
    }

    val choosePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onChoosePhotoClick(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        EditOption(
            onClick = {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureLauncher.launch(takePictureIntent)
            },
            icon = Icons.Default.CameraAlt,
            text = stringResource(id = R.string.take_photo_text)
        )
        EditOption(
            onClick = { choosePhotoLauncher.launch("image/*") },
            icon = Icons.Default.PhotoLibrary,
            text = stringResource(id = R.string.choose_photo_text)
        )
    }
}

private fun saveImageToGallery(context: Context, bitmap: Bitmap): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    uri?.let { imageUri ->
        resolver.openOutputStream(imageUri)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }
        return imageUri
    }
    return null
}

@Composable
fun EditOption(
    onClick: () -> Unit,
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun Header(
    fullName: String,
    editClick: () -> Unit,
    profileImageUri: Uri? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
        ) {
            Image(
                painter = if (profileImageUri != null) {
                    rememberImagePainter(profileImageUri)
                } else {
                    painterResource(id = R.drawable.profile)
                },
                contentDescription = stringResource(id = R.string.profile_image_description),
                modifier = Modifier
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Badge(
                content = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.edit_icon_description),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .size(26.dp)
                    .clickable(onClick = { editClick() }),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = fullName,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Composable
fun DisplayImage() {

}

@Composable
fun Experience(navController: NavController, experiencesList: List<JobExperience>) {
    ListField(
        title = R.string.candidate_jobExperience_title,
        emptyField = R.string.emptyExperience_text,
        onAddClick = {
            AddExperienceViewModel.instance.editingJobExperience = null
            navController.navigate("addExperience")
        },
        onClick = { jobExperience, index ->
            AddExperienceViewModel.instance.editingJobExperience = jobExperience
            AddExperienceViewModel.instance.editingIndex = index
            navController.navigate("addExperience")
        },
        itemsList = experiencesList,
    ) {
        Text(
            it.position,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            it.company,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
        )
        val dateRange = if (it.endDate != null) {
            "${it.startDate} - ${it.endDate}"
        } else {
            "${it.startDate} - {${stringResource(id = R.string.present_text)}"
        }
        Text(
            dateRange,
            style = MaterialTheme.typography.bodyMedium
        )
        it.description?.let { description ->
            Text(
                description,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

    }
}


@Composable
fun Studies(navController: NavController, studiesList: List<Study>) {
    ListField(
        title = R.string.candidate_studies_title,
        emptyField = R.string.emptyStudies_text,
        onAddClick = {
            AddStudyViewModel.instance.editingStudy = null
            navController.navigate("addStudy")
        },
        onClick = { study, index ->
            AddStudyViewModel.instance.editingStudy = study
            AddStudyViewModel.instance.editingIndex = index
            navController.navigate("addStudy")
        },
        itemsList = studiesList,
    ) {
        Text(
            text = it.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = it.school,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
        )
        val dateRange = if (it.endDate != null) {
            "${it.startDate} - ${it.endDate}"
        } else {
            "${it.startDate} - Present"
        }
        Text(
            dateRange,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun SoftSkills(
    navController: NavController,
    softSkillsList: List<String>,
) {
    ListField(
        title = R.string.candidate_softskills_title,
        emptyField = R.string.emptySoftskills_text,
        onAddClick = {
            val candidateProfileViewModel = CandidateProfileViewModel.getInstance()
            val addSoftskillViewModel = AddSoftskillViewModel.instance

            addSoftskillViewModel.editingSoftSkills.clear()
            addSoftskillViewModel.editingSoftSkills.addAll(candidateProfileViewModel.softSkills)
            navController.navigate("addSoftSkill")
        },
        itemsList = softSkillsList,
    ) {
        SuggestionChip(
            onClick = {
            },
            label = {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            border = SuggestionChipDefaults.suggestionChipBorder(
                enabled = true,
                borderColor = MaterialTheme.colorScheme.secondary,
                borderWidth = 2.dp,
            )
        )
    }
}

@Composable
fun Languages(navController: NavController, languagesList: List<LanguageSkill>) {
    ListField(
        title = R.string.candidate_languages_title,
        emptyField = R.string.emptyLanguages_text,
        onAddClick = {
            AddLanguageViewModel.instance.editingLanguage = null
            navController.navigate("addLanguage")
        },
        onClick = { language, index ->
            AddLanguageViewModel.instance.editingLanguage = language
            AddLanguageViewModel.instance.editingIndex = index
            navController.navigate("addLanguage")
        },
        itemsList = languagesList,
    ) {
        Text(
            it.language,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(it.level.name)
        if (it.academicTitle != null) {
            Text(
                it.academicTitle,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun Preferences(navController: NavController, preferences: MutableState<CandidatePreferences?>) {

    SingleField(
        title = R.string.candidate_preferences_title,
        onEditClick = {
            AddPreferencesViewModel.instance.editingPreference =
                CandidateProfileViewModel.getInstance().preferences.value
            navController.navigate("addPreferences")
        },
        editing = preferences.value != null,
        onAddClick = {
            navController.navigate("addPreferences")
        },
    ) {
        if (preferences.value == null) {
            Text(stringResource(id = R.string.emptyPreferences_text))
            return@SingleField
        }
        val preferencesValue = preferences.value!!
        Text(
            text = stringResource(id = R.string.salaryRange_text),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = preferencesValue.salaryRange.toStringResource(LocalContext.current)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.jobType_text),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = preferencesValue.jobTypeOptions.toStringResource(LocalContext.current)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.workingDayType_text),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = preferencesValue.workingDayType.toStringResource(LocalContext.current)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.contractType_text),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = preferencesValue.contractTypeOptions.toStringResource(LocalContext.current)
        )

    }
}

@Composable
fun SingleField(
    title: Int,
    onEditClick: () -> Unit = {},
    onAddClick: () -> Unit,
    editing: Boolean,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.secondary
            )
            OutlinedButton(
                onClick = {
                    if (editing) {
                        onEditClick()
                    } else {
                        onAddClick()
                    }
                },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 8.dp, horizontal = 2.dp),
                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                content = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (!editing) Icons.Default.Add else Icons.Default.Edit,
                            contentDescription = if (!editing) stringResource(id = R.string.add_icon_description) else stringResource(
                                id = R.string.edit_icon_description
                            ),
                        )
                        Text(
                            text = if (!editing) stringResource(id = R.string.add_text_button) else stringResource(
                                id = R.string.edit_text
                            ),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun <T> ListField(
    title: Int,
    emptyField: Int,
    onAddClick: () -> Unit,
    onClick: (item: T, index: Int) -> Unit = { item, index -> },
    itemsList: List<T>,
    itemRenderer: @Composable (T) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.secondary
            )
            OutlinedButton(
                onClick = onAddClick,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 8.dp, horizontal = 2.dp),
                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                content = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add_icon_description),
                        )
                        Text(
                            text = stringResource(id = R.string.add_text_button),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (itemsList.isEmpty()) {
                        Text(stringResource(id = emptyField))
                    } else {
                        itemsList.forEachIndexed() { index, item ->
                            Column(modifier = Modifier
                                .clickable {
                                    onClick(item, index)
                                }
                                .fillMaxWidth()
                            ) {
                                itemRenderer(item)
                            }
                        }

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    val candidate = CandidateInformation(
        description = "",
        studies = listOf(),
        softskills = listOf(),
        name = "Paco",
        lastname = "Garcia",
        location = "",
        languages = listOf(),
        jobExperience = listOf()
    )
    CandidateCV(rememberNavController())
}
