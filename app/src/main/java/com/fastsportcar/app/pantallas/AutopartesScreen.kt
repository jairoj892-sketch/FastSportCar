
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
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastsportcar.app.data.local.AutoparteEntity
import com.fastsportcar.app.ui.theme.*
import com.fastsportcar.app.viewmodel.MainViewModel

@Composable
fun AutopartesScreen(
    vm: MainViewModel,
    back: () -> Unit
) {
    val list by vm.autopartes.collectAsStateWithLifecycle()

    var id by rememberSaveable { mutableIntStateOf(0) }
    var n by rememberSaveable { mutableStateOf("") }
    var m by rememberSaveable { mutableStateOf("") }
    var mod by rememberSaveable { mutableStateOf("") }
    var d by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var stock by rememberSaveable { mutableStateOf("") }
    var msg by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .padding(16.dp)
    ) {
        EncabezadoPantalla("Autopartes", back)

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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Inventory2,
                        contentDescription = null,
                        tint = RojoPrincipal,
                        modifier = Modifier.size(26.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = if (id == 0) "Registrar autoparte" else "Editar autoparte",
                        color = TextoOscuro,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = n,
                    onValueChange = {
                        n = it
                        msg = ""
                    },
                    label = { Text("Nombre de la autoparte") },
                    leadingIcon = {
                        Icon(Icons.Default.Inventory2, contentDescription = null)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = m,
                        onValueChange = {
                            m = it
                            msg = ""
                        },
                        label = { Text("Marca") },
                        leadingIcon = {
                            Icon(Icons.Default.Category, contentDescription = null)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = mod,
                        onValueChange = {
                            mod = it
                            msg = ""
                        },
                        label = { Text("Modelo") },
                        leadingIcon = {
                            Icon(Icons.Default.Tune, contentDescription = null)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = d,
                    onValueChange = { d = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    minLines = 2,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = price,
                        onValueChange = {
                            price = it
                            msg = ""
                        },
                        label = { Text("Precio") },
                        leadingIcon = {
                            Icon(Icons.Default.AttachMoney, contentDescription = null)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = stock,
                        onValueChange = {
                            stock = it
                            msg = ""
                        },
                        label = { Text("Existencia") },
                        leadingIcon = {
                            Icon(Icons.Default.Numbers, contentDescription = null)
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true
                    )
                }

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
                        val p = price.toDoubleOrNull()
                        val s = stock.toIntOrNull()

                        if (
                            n.isBlank() || m.isBlank() || mod.isBlank() ||
                            p == null || !p.isFinite() || p < 0 ||
                            s == null || s < 0
                        ) {
                            msg = "Completa los campos con valores válidos."
                        } else {
                            vm.guardar(
                                AutoparteEntity(
                                    id,
                                    n.trim(),
                                    m.trim(),
                                    mod.trim(),
                                    d.trim(),
                                    p,
                                    s
                                )
                            )

                            id = 0
                            n = ""
                            m = ""
                            mod = ""
                            d = ""
                            price = ""
                            stock = ""
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
                        text = if (id == 0) "Guardar autoparte" else "Actualizar autoparte",
                        fontWeight = FontWeight.Bold
                    )
                }

                if (id != 0) {
                    TextButton(
                        onClick = {
                            id = 0
                            n = ""
                            m = ""
                            mod = ""
                            d = ""
                            price = ""
                            stock = ""
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
                text = "Inventario de autopartes",
                color = TextoOscuro,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Inventory2,
                        contentDescription = null,
                        tint = TextoSecundario,
                        modifier = Modifier.size(42.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Todavía no hay autopartes registradas.",
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
                                        imageVector = Icons.Default.Inventory2,
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
                                        text = "${x.marca} · ${x.modelo}",
                                        color = TextoSecundario,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            if (x.descripcion.isNotBlank()) {
                                Text(
                                    text = x.descripcion,
                                    color = TextoSecundario,
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "$${"%.2f".format(x.precio)}",
                                    color = RojoPrincipal,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Existencias: ${x.stock}",
                                    color = TextoOscuro,
                                    fontWeight = FontWeight.SemiBold,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = {
                                        id = x.id
                                        n = x.nombre
                                        m = x.marca
                                        mod = x.modelo
                                        d = x.descripcion
                                        price = x.precio.toString()
                                        stock = x.stock.toString()
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
                                        vm.borrarAutoparte(x.id)

                                        if (id == x.id) {
                                            id = 0
                                            n = ""
                                            m = ""
                                            mod = ""
                                            d = ""
                                            price = ""
                                            stock = ""
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
