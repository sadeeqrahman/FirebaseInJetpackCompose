package com.sadeeq.encoders.firebaseinjetpackcompose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sadeeq.encoders.firebaseinjetpackcompose.screen.LoginScreen
import com.sadeeq.encoders.firebaseinjetpackcompose.ui.theme.FirebaseInJetpackComposeTheme
import com.sadeeq.encoders.firebaseinjetpackcompose.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseInJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val authViewModel: AuthViewModel = hiltViewModel()
                    val isUserAuthenticated by authViewModel.isUserAuthenticated.observeAsState(
                        false
                    )
                    if (isUserAuthenticated) {
                        Log.e("zzzzzzzzzzzzzzzzzzzzzzz","Login")
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Log.e("zzzzzzzzzzzzzzzzzzzzzzz","Not Login")
                        LoginScreen()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseInJetpackComposeTheme {
        LoginScreen()
    }
}