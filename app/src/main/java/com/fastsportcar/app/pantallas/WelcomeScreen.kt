
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastsportcar.app.ui.theme.*

@Composable
fun WelcomeScreen(
    start: () -> Unit,
    privacy: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Blanco,
                        FondoClaro,
                        Color(0xFFFFEEEE)
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp)
                .background(
                    RojoPrincipal,
                    RoundedCornerShape(
                        bottomStart = 55.dp,
                        bottomEnd = 55.dp
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(112.dp),
                shape = CircleShape,
                color = Blanco,
                shadowElevation = 8.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = "FastSportCar",
                        tint = RojoPrincipal,
                        modifier = Modifier.size(68.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "FASTSPORTCAR",
                color = Blanco,
                fontSize = 29.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "SISTEMA DE ADMINISTRACIÓN",
                color = Blanco.copy(alpha = 0.9f),
                style = MaterialTheme.typography.labelMedium,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Blanco
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = RojoPrincipal.copy(alpha = 0.10f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Inventory2,
                            contentDescription = null,
                            tint = RojoPrincipal,
                            modifier = Modifier
                                .padding(12.dp)
                                .size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "¡Bienvenido!",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextoOscuro
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Administra tu negocio de autopartes de manera sencilla y organizada.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextoSecundario,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    Button(
                        onClick = start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RojoPrincipal
                        )
                    ) {
                        Text(
                            "Comenzar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = privacy,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = RojoPrincipal
                        )
                    ) {
                        Icon(
                            Icons.Default.PrivacyTip,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Aviso de privacidad")
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "FASTSPORTCAR",
                color = RojoPrincipal,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = "Gestión de autopartes",
                color = TextoSecundario,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
