
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastsportcar.app.data.local.VendedorEntity
import com.fastsportcar.app.viewmodel.MainViewModel
import com.fastsportcar.app.ui.theme.*

@Composable
fun VendedoresScreen(
    vm: MainViewModel,
    back: () -> Unit
) {
    val list by vm.vendedores.collectAsStateWithLifecycle()

    var id by rememberSaveable { mutableIntStateOf(0) }
    var n by rememberSaveable { mutableStateOf("") }
    var t by rememberSaveable { mutableStateOf("") }
    var e by rememberSaveable { mutableStateOf("") }
    var msg by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .padding(16.dp)
    ) {
        EncabezadoPantalla("Vendedores", back)

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Blanco),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = RojoPrincipal.copy(alpha = 0.10f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = null,
                            tint = RojoPrincipal,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            if (id == 0) "Registrar vendedor" else "Editar vendedor",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextoOscuro
                        )
                        Text(
                            "Completa los datos del vendedor",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextoSecundario
                        )
                    }
                }

                OutlinedTextField(
                    value = n,
                    onValueChange = {
                        n = it
                        msg = ""
                    },
                    label = { Text("Nombre completo") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = t,
                    onValueChange = {
                        t = it
                        msg = ""
                    },
                    label = { Text("Teléfono") },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = e,
                    onValueChange = {
                        e = it
                        msg = ""
                    },
                    label = { Text("Correo electrónico") },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                if (msg.isNotBlank()) {
                    Text(
                        msg,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = {
                        if (n.isBlank() || t.isBlank() || !e.contains("@")) {
                            msg = "Ingresa nombre, teléfono y correo válido."
                        } else {
                            vm.guardar(
                                VendedorEntity(
                                    id,
                                    n.trim(),
                                    t.trim(),
                                    e.trim()
                                )
                            )

                            id = 0
                            n = ""
                            t = ""
                            e = ""
                            msg = ""
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RojoPrincipal
                    )
                ) {
                    Text(
                        if (id == 0) "Guardar vendedor" else "Actualizar vendedor",
                        fontWeight = FontWeight.Bold
                    )
                }

                if (id != 0) {
                    TextButton(
                        onClick = {
                            id = 0
                            n = ""
                            t = ""
                            e = ""
                            msg = ""
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Cancelar edición", color = TextoSecundario)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Vendedores registrados",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextoOscuro
            )

            Surface(
                color = RojoPrincipal,
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    "${list.size} registros",
                    color = Blanco,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (list.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Store,
                        contentDescription = null,
                        tint = TextoSecundario,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Todavía no hay vendedores registrados",
                        color = TextoSecundario
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(list, key = { it.id }) { x ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Blanco
                        ),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = RojoPrincipal.copy(alpha = 0.10f)
                                ) {
                                    Icon(
                                        Icons.Default.Person,
                                        contentDescription = null,
                                        tint = RojoPrincipal,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        x.nombre,
                                        fontWeight = FontWeight.Bold,
                                        color = TextoOscuro
                                    )
                                    Text(
                                        "Vendedor #${x.id}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextoSecundario
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                "Teléfono: ${x.telefono}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextoOscuro
                            )
                            Text(
                                "Correo: ${x.correo}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextoSecundario
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = {
                                        id = x.id
                                        n = x.nombre
                                        t = x.telefono
                                        e = x.correo
                                        msg = ""
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Edit,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Editar")
                                }

                                TextButton(
                                    onClick = {
                                        vm.borrarVendedor(x.id)
                                        if (id == x.id) {
                                            id = 0
                                            n = ""
                                            t = ""
                                            e = ""
                                        }
                                    },
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
