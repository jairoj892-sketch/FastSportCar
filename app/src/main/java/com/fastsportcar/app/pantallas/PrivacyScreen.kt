
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastsportcar.app.ui.theme.*

@Composable
fun PrivacyScreen(back: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(RojoPrincipal)
                .padding(horizontal = 20.dp, vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = Blanco
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "Aviso de privacidad",
                    color = Blanco,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "FASTSPORTCAR",
                    color = Blanco.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.labelSmall,
                    letterSpacing = 2.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Blanco
                ),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = RojoPrincipal.copy(alpha = 0.10f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = RojoPrincipal,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(42.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Tu información es importante",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextoOscuro,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Conoce cómo se utiliza y almacena la información dentro de esta aplicación.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextoSecundario,
                        textAlign = TextAlign.Center
                    )
                }
            }

            PrivacySection(
                icon = Icons.Default.Visibility,
                title = "Uso de la aplicación",
                description = "FastSportCar es una aplicación académica de demostración para administrar autopartes, clientes, vendedores y ventas."
            )

            PrivacySection(
                icon = Icons.Default.Storage,
                title = "Almacenamiento de datos",
                description = "Los datos capturados se almacenan localmente en SQLite mediante Room. Esta versión no transmite información a servidores externos ni comparte datos con terceros."
            )

            PrivacySection(
                icon = Icons.Default.Security,
                title = "Protección de información",
                description = "Se recomienda utilizar datos ficticios durante las pruebas. El usuario debe proteger el acceso físico a su dispositivo."
            )

            PrivacySection(
                icon = Icons.Default.Warning,
                title = "Consideraciones importantes",
                description = "Al desinstalar la aplicación o borrar sus datos desde Android, la información local puede eliminarse. El inicio de sesión es demostrativo y no constituye autenticación real."
            )

            PrivacySection(
                icon = Icons.Default.Security,
                title = "Permisos y pagos",
                description = "La aplicación no procesa pagos ni solicita permisos de ubicación, cámara, contactos o archivos."
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = RojoPrincipal.copy(alpha = 0.08f)
                )
            ) {
                Text(
                    text = "Este aviso corresponde a la versión académica y no sustituye un aviso legal para un servicio comercial.",
                    modifier = Modifier.padding(18.dp),
                    color = TextoOscuro,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = back,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RojoPrincipal
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Regresar",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun PrivacySection(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blanco
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = RojoPrincipal,
                modifier = Modifier
                    .size(26.dp)
                    .padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = title,
                    color = TextoOscuro,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = description,
                    color = TextoSecundario,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
