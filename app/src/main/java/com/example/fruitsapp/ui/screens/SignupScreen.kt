package com.example.fruitsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fruitsapp.routes.Screen
import com.example.fruitsapp.viewmodel.UserViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignupScreen(navController: NavController) {
    val viewModel = getViewModel<UserViewModel>()
    var username by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var signupSuccess by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    if (signupSuccess) {
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo(Screen.SignupScreen.route) { inclusive = true }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Error", style = MaterialTheme.typography.titleLarge) },
            text = { Text(errorMessage, style = MaterialTheme.typography.titleMedium) },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(text = "Ok", style = MaterialTheme.typography.titleMedium)
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Icon",
            modifier = Modifier.size(100.dp)
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", style = MaterialTheme.typography.titleLarge) },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = Color(0xFF000080),
                unfocusedLeadingIconColor = Color(0xFFb3b3ff),
                focusedLabelColor = Color(0xFF000080),
                unfocusedLabelColor = Color(0xFFb3b3ff),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF000080),
                unfocusedIndicatorColor = Color(0xFFb3b3ff),
                focusedPlaceholderColor = Color(0xFF000080),
                unfocusedPlaceholderColor = Color(0xFFb3b3ff)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Username Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Password", style = MaterialTheme.typography.titleLarge) },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = Color(0xFF000080),
                unfocusedLeadingIconColor = Color(0xFFb3b3ff),
                focusedLabelColor = Color(0xFF000080),
                unfocusedLabelColor = Color(0xFFb3b3ff),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF000080),
                unfocusedIndicatorColor = Color(0xFFb3b3ff),
                focusedPlaceholderColor = Color(0xFF000080),
                unfocusedPlaceholderColor = Color(0xFFb3b3ff)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (username.isBlank()) {
                    errorMessage = "Username can not be empty"
                    showDialog = true
                } else if (pass.isBlank()) {
                    errorMessage = "Password can not be empty"
                    showDialog = true
                } else {
                    viewModel.signup(username, pass) { success ->
                        if (success) {
                            signupSuccess = true
                        } else {
                            showDialog = true
                        }
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(Color(0xFF000080)),
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Sign Up", style = MaterialTheme.typography.titleLarge)
        }
        ClickableText(
            text = AnnotatedString("Already have an account? Log in here"),
            onClick = {
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.SignupScreen.route) { inclusive = true }
                }
            },
            modifier = Modifier.padding(),
        )

    }
}
