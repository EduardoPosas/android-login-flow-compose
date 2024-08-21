package com.example.loginflowapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.loginflowapp.datastore.UserPreferences
import com.example.loginflowapp.datastore.data.UserPreferencesSerializer
import com.example.loginflowapp.ui.theme.LoginFlowAppTheme
import dagger.hilt.android.AndroidEntryPoint

/*
* Create a DataStore instance using datastore delegate, with Context as receiver
* the delegate has two mandatory parameters
* @Param the name of the file that DataStore will act on
* @Param the serializer for the type used with DataStore
* */
//private const val DATA_STORE_FILE_NAME = "user_prefs.pb"
//// the datastore delegate ensures that we have a single instance of DataStore in our app
//private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
//    fileName = DATA_STORE_FILE_NAME,
//    serializer = UserPreferencesSerializer
//)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginFlowAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginFlowApp()
                }
            }
        }
    }
}