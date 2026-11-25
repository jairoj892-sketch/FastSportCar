
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    error: String,
    back: () -> Unit,
    login: (String, String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        Color(0xFFFFEEEE),
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = back) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Regresar",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                modifier = Modifier.size(82.dp),
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Filled.DirectionsCar,
                        contentDescription = "FastSportCar",
                        tint = Color.White,
                        modifier = Modifier.size(52.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fast",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "SportCar",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "SISTEMA DE ADMINISTRACIÓN",
                fontSize = 10.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "¡Bienvenido!",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Ingresa tus datos para acceder al sistema.",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = "Correo electrónico",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("ejemplo@correo.com")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Email,
                                contentDescription = null
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Contraseña",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Ingresa tu contraseña")
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Lock,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisible = !passwordVisible
                                }
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible) {
                                        Icons.Filled.VisibilityOff
                                    } else {
                                        Icons.Filled.Visibility
                                    },
                                    contentDescription = "Mostrar u ocultar contraseña"
                                )
                            }
                        },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
                        )
                    )

                    if (error.isNotBlank()) {
                        Spacer(modifier = Modifier.height(14.dp))

                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFFFFE5E5),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = error,
                                modifier = Modifier.padding(12.dp),
                                color = Color(0xFFB3261E),
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {
                            login(email.trim(), pass)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Iniciar sesión",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Acceso demostrativo",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Esta versión utiliza una validación de prueba y no verifica cuentas en un servidor.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "FASTSPORTCAR ©",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
