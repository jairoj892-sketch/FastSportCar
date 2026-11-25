
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastsportcar.app.ui.theme.*

@Composable
fun EncabezadoPantalla(
    title: String,
    back: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                RojoPrincipal,
                RoundedCornerShape(18.dp)
            )
            .padding(horizontal = 12.dp, vertical = 14.dp),
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

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = Blanco,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        TextButton(
            onClick = back,
            colors = ButtonDefaults.textButtonColors(
                contentColor = Blanco
            )
        ) {
            Text(
                text = "Volver",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
