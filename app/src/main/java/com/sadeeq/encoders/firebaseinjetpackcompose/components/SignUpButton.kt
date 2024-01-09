package com.sadeeq.encoders.firebaseinjetpackcompose.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.sadeeq.encoders.firebaseinjetpackcompose.HomeActivity
import com.sadeeq.encoders.firebaseinjetpackcompose.R
import com.sadeeq.encoders.firebaseinjetpackcompose.screen.HomeScreen
import com.sadeeq.encoders.firebaseinjetpackcompose.selead.AuthResult
import com.sadeeq.encoders.firebaseinjetpackcompose.viewmodel.AuthViewModel


@Composable
fun SignUpButton(username: String, password: String, onClick: (String, String) -> Unit) {
    val context = LocalContext.current
    val authViewModel: AuthViewModel = hiltViewModel()

    val signUpResult by authViewModel.signUpResult.observeAsState()

    when (signUpResult) {
        is AuthResult.Success -> {
            // Handle successful sign-up
            val user = (signUpResult as AuthResult.Success).user
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
        is AuthResult.Error -> {
            // Handle sign-up error
            val exception = (signUpResult as AuthResult.Error).exception
            Toast.makeText(context, exception.message.toString(), Toast.LENGTH_SHORT).show()
        }
        // Add more states as needed
        else -> {

        }
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.purple_200)
        ), onClick = {
            if (username.isEmpty()) {
                Toast.makeText(context, "Username: is Empty", Toast.LENGTH_SHORT).show()
            } else {
                authViewModel.signUp(username,password)
            }
        },

        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(10.dp)
            .border(1.dp, MaterialTheme.colorScheme.primaryContainer, CircleShape)
    ) {


        Text(
            "SignUp", style = MaterialTheme.typography.titleSmall.copy(color = Color.Blue)
        )
    }

}


