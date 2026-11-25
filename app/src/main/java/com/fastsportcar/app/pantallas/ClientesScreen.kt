
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
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastsportcar.app.data.local.ClienteEntity
import com.fastsportcar.app.ui.theme.*
import com.fastsportcar.app.viewmodel.MainViewModel

@Composable
fun ClientesScreen(
    vm: MainViewModel,
    back: () -> Unit
) {
    val list by vm.clientes.collectAsStateWithLifecycle()

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
        EncabezadoPantalla("Clientes", back)

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Blanco
            ),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = if (id == 0) "Registrar cliente" else "Editar cliente",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = TextoOscuro
                )

                Spacer(modifier = Modifier.height(16.dp))

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

                Spacer(modifier = Modifier.height(10.dp))

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
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

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
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    singleLine = true
                )

                if (msg.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = msg,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (n.isBlank() || t.isBlank() || !e.contains("@")) {
                            msg = "Ingresa nombre, teléfono y correo válido."
                        } else {
                            vm.guardar(
                                ClienteEntity(
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
                    Icon(
                        Icons.Default.Save,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = if (id == 0) "Guardar cliente" else "Actualizar cliente",
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
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cancelar edición", color = RojoPrincipal)
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
                text = "Clientes registrados",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextoOscuro
            )

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = RojoPrincipal.copy(alpha = 0.10f)
            ) {
                Text(
                    text = "${list.size} registros",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    ),
                    color = RojoPrincipal,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelMedium
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
                Text(
                    text = "Todavía no hay clientes registrados.",
                    color = TextoSecundario,
                    style = MaterialTheme.typography.bodyMedium
                )
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
                            modifier = Modifier.padding(15.dp)
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
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .size(26.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = x.nombre,
                                        color = TextoOscuro,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(
                                        text = "Cliente #${x.id}",
                                        color = TextoSecundario,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Teléfono: ${x.telefono}",
                                color = TextoSecundario,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = "Correo: ${x.correo}",
                                color = TextoSecundario,
                                style = MaterialTheme.typography.bodyMedium
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
                                        tint = RojoPrincipal
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text("Editar", color = RojoPrincipal)
                                }

                                TextButton(
                                    onClick = {
                                        vm.borrarCliente(x.id)

                                        if (id == x.id) {
                                            id = 0
                                            n = ""
                                            t = ""
                                            e = ""
                                            msg = ""
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = RojoPrincipal
                                    )

                                    Spacer(modifier = Modifier.width(5.dp))

                                    Text("Eliminar", color = RojoPrincipal)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
