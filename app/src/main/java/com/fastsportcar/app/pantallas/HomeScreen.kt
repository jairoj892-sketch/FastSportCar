
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastsportcar.app.ui.theme.*

@Composable
fun HomeScreen(
    privacy: () -> Unit,
    openAutopartes: () -> Unit,
    openClientes: () -> Unit,
    openVendedores: () -> Unit,
    openVentas: () -> Unit,
    exit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    RojoPrincipal,
                    RoundedCornerShape(
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
                .padding(horizontal = 24.dp, vertical = 30.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = Blanco.copy(alpha = 0.18f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DirectionsCar,
                            contentDescription = null,
                            tint = Blanco,
                            modifier = Modifier.padding(12.dp).size(34.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "FASTSPORTCAR",
                            color = Blanco,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp,
                            letterSpacing = 1.5.sp
                        )

                        Text(
                            text = "Sistema de administración",
                            color = Blanco.copy(alpha = 0.85f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Panel principal",
                    color = Blanco,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Bienvenido. ¿Qué deseas administrar hoy?",
                    color = Blanco.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Text(
                text = "MÓDULOS DEL SISTEMA",
                color = TextoSecundario,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            MenuItem(
                title = "Autopartes",
                description = "Registra, consulta y administra productos.",
                icon = Icons.Default.Inventory2,
                click = openAutopartes
            )

            MenuItem(
                title = "Clientes",
                description = "Consulta y administra la información de clientes.",
                icon = Icons.Default.People,
                click = openClientes
            )

            MenuItem(
                title = "Vendedores",
                description = "Administra los datos de tus vendedores.",
                icon = Icons.Default.Store,
                click = openVendedores
            )

            MenuItem(
                title = "Ventas",
                description = "Registra y consulta las ventas realizadas.",
                icon = Icons.Default.PointOfSale,
                click = openVentas
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedButton(
                onClick = privacy,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = RojoPrincipal
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(9.dp))

                Text(
                    text = "Aviso de privacidad",
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = exit,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = RojoPrincipal
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "FASTSPORTCAR • Gestión de autopartes",
                modifier = Modifier.fillMaxWidth(),
                color = TextoSecundario,
                style = MaterialTheme.typography.labelSmall,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
private fun MenuItem(
    title: String,
    description: String,
    icon: ImageVector,
    click: () -> Unit
) {
    Card(
        onClick = click,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blanco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = RojoPrincipal.copy(alpha = 0.10f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = RojoPrincipal,
                    modifier = Modifier
                        .padding(13.dp)
                        .size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = TextoOscuro,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    color = TextoSecundario,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
