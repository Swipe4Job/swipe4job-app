package cat.dam.grup2.swipe4job_app.shared
import cat.dam.grup2.swipe4job_app.R
import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.content.ContextCompat

sealed class Permissions(vararg val permissions: String) {
    // Individual permissions
    object PermCamera : Permissions(Manifest.permission.CAMERA)

    // Bundled permissions (per quan s'ha de demanar més d'un permís)
    object PermImagePick : Permissions(*getImagePickPermissions())
    object PermCamImgSave : Permissions(*getImgCamPermission())
    object PermCamVidImg : Permissions(*getImgVidCamPermission())
    object PermImgVid : Permissions(*getImgVidPermission())
    object PermAudioPick : Permissions(*getAudioPermission())

    // Grouped permissions (per quan s'ha de demanar un grup de permisos)
    object PermLocation : Permissions(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    companion object {
        private fun getImagePickPermissions(): Array<String> {
            return if (PermissionManager.sdkEqOrAbove33()) {
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            } else if (PermissionManager.sdkEqOrAbove29()) {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                arrayOf(
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        private fun getImgCamPermission(): Array<String> {
            return if (PermissionManager.sdkEqOrAbove33()) {
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            } else if (PermissionManager.sdkEqOrAbove29()) {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            } else {
                arrayOf(
                    Manifest.permission.CAMERA,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        private fun getImgVidCamPermission(): Array<String> {
            return if (PermissionManager.sdkEqOrAbove33()) {
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO
                )
            } else if (PermissionManager.sdkEqOrAbove29()) {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            } else {
                arrayOf(
                    Manifest.permission.CAMERA,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        private fun getAudioPermission(): Array<String> {
            return if (PermissionManager.sdkEqOrAbove33()) {
                arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
            } else if (PermissionManager.sdkEqOrAbove29()) {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                arrayOf(
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        private fun getImgVidPermission(): Array<String> {
            return if (PermissionManager.sdkEqOrAbove33()) {
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO)
            } else if (PermissionManager.sdkEqOrAbove29()) {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                arrayOf(
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }
    }
}

class PermissionManager(var activity: ComponentActivity) : ComponentActivity() {
    private val requiredPermissions = mutableListOf<Permissions>()
    private var rationaleDescription: String? = null
    private var rationaleTitle: String? = null
    private var permanentlyDeniedDescription: String? = null
    private var callback: (Boolean) -> Unit = {}
    private var intent: Intent? = null
    private var detailedCallback: (Map<String, Boolean>) -> Unit = {}
    private val deniedList = arrayListOf<String>()
    private lateinit var permissionCheck: ActivityResultLauncher<Array<String>>
    private lateinit var sharedPreferences: SharedPreferences
    private val prefName = "permissions_pref"

    init {
        initializePermissionCheck()
        initializeSharedPreferences()
    }

    private fun initializePermissionCheck() {
        permissionCheck =
            activity.registerForActivityResult(RequestMultiplePermissions()) { grantResults ->
                sendResultAndCleanUp(grantResults)
            }
    }

    private fun initializeSharedPreferences() {
        sharedPreferences = activity.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }


    companion object {
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
        fun sdkEqOrAbove33() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
        fun sdkEqOrAbove29() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
        fun sdkEqOrAbove30() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
        fun sdkEqOrAbove31() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
        fun sdkEqOrAbove28() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

        @SuppressLint("ObsoleteSdkInt")
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
        fun sdkEqOrAbove23() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun rationale(
        description: String, title: String = activity.getString(R.string.permission_title)
    ): PermissionManager {
        rationaleDescription = description
        rationaleTitle = title
        return this
    }

    fun request(vararg permission: Permissions): PermissionManager {
        requiredPermissions.addAll(permission)
        return this
    }

    fun permissionPermanentlyDeniedIntent(intent: Intent): PermissionManager {
        this.intent = intent
        return this
    }

    fun permissionPermanentlyDeniedContent(description: String = ""): PermissionManager {
        this.permanentlyDeniedDescription =
            description.ifEmpty { activity.getString(R.string.permission_description) }
        return this
    }

    fun checkAndRequestPermission(callback: (Boolean) -> Unit) {
        this.callback = callback
        handlePermissionRequest()
    }

    fun checkAndRequestDetailedPermission(callback: (Map<String, Boolean>) -> Unit) {
        this.detailedCallback = callback
        handlePermissionRequest()
    }

    private fun handlePermissionRequest() {
        // Quan l'usuari ha denegat el permís almenys una vegada -> Mostra un diàleg de justificació
        // Sinó si l'usuari mai ha sol·licitat el permís -> Mostra una finestra emergent de permisos
        // sinó L'usuari ha denegat permanentment el permís -> Configuració

        activity.let { activity ->
            if (areAllPermissionsGranted(activity)) {
                //Tots els permisos necessaris acceptats
                sendPositiveResult()
            } else if (shouldShowPermissionRationale(activity)) {
                //Si l'usuari ha denegat el permís almenys una vegada i no ha marcat "No tornis a preguntar"
                //Mostrarà un diàleg de justificació una sola vegada si no s'indica el contrari amb PermissionStatus
                getPermissionList().forEach {
                    // marca tots els permisos com ja demanats perquè no es torni a mostrar el diàleg de justificació
                    //en versions antigues no cal controlar nombre vegades que s'ha demanat el permís
                    setAlreadyAskedForPermission(it, true)
                }
                //Mostra un diàleg de justificació
                val requiresRationaleList =
                    getPermissionList().map { Pair(it, requiresRationale(activity, it)) }
                displayRationale(
                    activity,
                    getCommaSeparatedFormattedString(requiresRationaleList.filter { it.second }
                        .map { it.first })
                )
            } else {
                //Si l'usuari mai ha sol·licitat el permís o l'ha denegat permanentment
                if (getPermissionList().any { !getAlreadyAskedForPermission(it) }) {
                    //Si l'usuari mai ha sol·licitat el permís el demanem
                    requestPermissions()
                } else {
                    // Si l'usuari ha denegat permanentment un dels permisos necessaris donem opció de configuració
                    val permanentlyDeniedList =
                        getPermissionList().filter { isPermanentlyDenied(activity, it) }
                    if (permanentlyDeniedList.isNotEmpty()) {
                        displayPermanentlyDenied(
                            activity,
                            getCommaSeparatedFormattedString(permanentlyDeniedList)
                        )
                        cleanUp()
                    }
                }
            }

        }
    }


    @SuppressLint("StringFormatInvalid")
    private fun displayRationale(activity: Context, permission: String?) {
        AlertDialog.Builder(activity)
            .setTitle(rationaleTitle ?: activity.getString(R.string.permission_title))
            .setMessage(
                rationaleDescription ?: activity.getString(
                    R.string.permission_description,
                    permission ?: ""
                )
            )
            .setCancelable(true)
            .setNegativeButton(activity.getString(R.string.no_thanks)) { dialog, _ ->
                dialog.dismiss()
                cleanUp()
            }
            .setPositiveButton(activity.getString(R.string.button_ok)) { _, _ -> requestPermissions() }
            .show()
    }

    @SuppressLint("StringFormatInvalid")
    private fun displayPermanentlyDenied(
        activity: Context,
        deniedPermissions: String?
    ) {
        AlertDialog.Builder(activity)
            .setTitle(activity.getString(R.string.permission_title))
            .setMessage(
                permanentlyDeniedDescription ?: activity.getString(
                    R.string.permission_description_permanently,
                    deniedPermissions
                )
            )
            .setCancelable(true)
            .setNegativeButton(activity.getString(R.string.no_thanks)) { dialog, _ ->
                dialog.dismiss()
                cleanUp()
            }
            .setPositiveButton(activity.getString(R.string.go_to_settings)) { _, _ ->
                val finalIntent = if (intent != null) {
                    intent
                } else {
                    val intent2 = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + activity.packageName)
                    )
                    intent2.addCategory(Intent.CATEGORY_DEFAULT)
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent2
                }
                activity.startActivity(finalIntent)
            }.show()
    }

    private fun sendPositiveResult() {
        sendResultAndCleanUp(getPermissionList().associateWith { true })
    }

    private fun sendResultAndCleanUp(grantResults: Map<String, Boolean>) {
        if (deniedList.isNotEmpty()) {
            activity.let {
                displayPermanentlyDenied(
                    it,
                    getCommaSeparatedFormattedString(deniedList)
                )
            }
        } else {
            callback(grantResults.all { it.value })
            detailedCallback(grantResults)
        }
        cleanUp()
    }

    private fun cleanUp() {
        requiredPermissions.clear()
        rationaleDescription = null
        permanentlyDeniedDescription = null
        deniedList.clear()
        callback = {}
        detailedCallback = {}
    }

    private fun requestPermissions() {
        val list = getPermissionList()
        val deniedList = list.filter { isPermanentlyDenied(activity, it) }
        this.deniedList.addAll(deniedList)
        val finalList = list.subtract(deniedList.toSet())
        permissionCheck.launch(finalList.toTypedArray())
    }

    private fun areAllPermissionsGranted(activity: ComponentActivity) =
        requiredPermissions.all { it.isGranted(activity) }

    private fun shouldShowPermissionRationale(activity: ComponentActivity) =
        requiredPermissions.any { it.requiresRationale(activity) }

    private fun getPermissionList(): Array<String> {
        val reqPermissions = requiredPermissions.flatMap { it.permissions.toList() }.toTypedArray()
        return reqPermissions
    }

    private fun Permissions.isGranted(activity: ComponentActivity) =
        permissions.all { hasPermission(activity, it) }

    private fun Permissions.requiresRationale(activity: ComponentActivity): Boolean {
        return permissions.any { activity.shouldShowRequestPermissionRationale(it) }
    }

    private fun requiresRationale(activity: ComponentActivity, permission: String) =
        activity.shouldShowRequestPermissionRationale(permission)

    private fun isPermanentlyDenied(activity: ComponentActivity, permission: String): Boolean {
        if (!hasPermission(activity, permission)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                //en versions noves si el permís no és sensible i està denegat és de forma permanent
                //si el permís és senseible i està denegat pot ser perquè pregunta cada vegada (caldrà veure si requereix rational)
                val permissionInfo =
                    activity.packageManager.getPermissionInfo(
                        permission,
                        PackageManager.GET_META_DATA
                    )
                val flags = permissionInfo.protectionFlags
                val isUserSensitive = (flags and PermissionInfo.PROTECTION_FLAG_INSTANT) != 0
                if (isUserSensitive) { // quan sigui un permís sensible i opció de preguntar cada vegada (per exemple càmera)
                    setAlreadyAskedForPermission(permission, false)
                    return requiresRationale(activity, permission)
                } else { //quan sigui un permís no sensible i s'hagi denegat completament
                    return requiresRationale(activity, permission)
                }
            } else {
                //en versions anteriors només és de forma permanent quan s'ha demanat mínim una vegada
                // i no requereix diàleg raonat
                if (getAlreadyAskedForPermission(permission)) {
                    return !requiresRationale(activity, permission)
                } else {
                    return false
                }
            }
        } else {
            return false //quan es té el permís aquest no és denegat de forma permanent
        }
    }

    private fun hasPermission(activity: ComponentActivity, permission: String) =
        ContextCompat.checkSelfPermission(
            activity,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    private fun getCommaSeparatedFormattedString(permissions: List<String>): String? {
        val newList = mapPermissionsToStrings(permissions)
        val list = newList.toMutableList()
        return if (list.size == 1) {
            list.first()
        } else {
            list.removeLast()
            val string = list.joinToString(", ")
            string + " , " + newList.last()
        }
    }

    private fun setAlreadyAskedForPermission(key: String, value: Boolean) {
        //per evitar preguntar més d'una vegada en versions superiors a R
        //Les dades emmagatzemades amb SharedPreferences són persistents fins que l'usuari
        //decideixi eliminar-les o fins que es desinstal·la l'aplicació.
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun getAlreadyAskedForPermission(key: String): Boolean {
        // per saber si s'ha preguntat alguna vegada en versions superiors a R
        return sharedPreferences.getBoolean(key, false)
    }

    private fun mapPermissionsToStrings(list: List<String>): List<String?> {
        return list.map {
            when (it) {
                Manifest.permission.INTERNET -> activity.getString(R.string.internet)
                Manifest.permission.READ_EXTERNAL_STORAGE -> activity.getString(R.string.read_external_storage)
                Manifest.permission.READ_MEDIA_IMAGES -> activity.getString(R.string.read_media_images)
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> activity.getString(R.string.write_external_storage)
                Manifest.permission.CAMERA -> activity.getString(R.string.camera)
                else -> "Other"
            }
        }
    }
}