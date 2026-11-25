
package com.fastsportcar.app.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fastsportcar.app.data.local.VentaEntity
import com.fastsportcar.app.viewmodel.MainViewModel
import com.fastsportcar.app.ui.theme.*

@Composable
fun VentasScreen(
    vm: MainViewModel,
    back: () -> Unit
) {
    val sales by vm.ventas.collectAsStateWithLifecycle()
    val clients by vm.clientes.collectAsStateWithLifecycle()
    val sellers by vm.vendedores.collectAsStateWithLifecycle()
    val parts by vm.autopartes.collectAsStateWithLifecycle()

    var c by rememberSaveable { mutableStateOf("") }
    var v by rememberSaveable { mutableStateOf("") }
    var a by rememberSaveable { mutableStateOf("") }
    var q by rememberSaveable { mutableStateOf("1") }
    var date by rememberSaveable { mutableStateOf("") }
    var editId by rememberSaveable { mutableIntStateOf(0) }
    var msg by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .padding(16.dp)
    ) {
        EncabezadoPantalla("Ventas", back)

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Blanco),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = RojoPrincipal.copy(alpha = 0.10f)
                    ) {
                        Icon(
                            Icons.Default.PointOfSale,
                            contentDescription = null,
                            tint = RojoPrincipal,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            if (editId == 0) "Registrar nueva venta" else "Actualizar venta",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium,
                            color = TextoOscuro
                        )
                        Text(
                            "Completa los datos de la operación",
                            color = TextoSecundario,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Text(
                    "Primero registra un cliente, vendedor y autoparte en sus respectivos módulos.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextoSecundario
                )

                OutlinedTextField(
                    value = c,
                    onValueChange = { c = it; msg = "" },
                    label = { Text("ID del cliente") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = v,
                    onValueChange = { v = it; msg = "" },
                    label = { Text("ID del vendedor") },
                    leadingIcon = {
                        Icon(Icons.Default.Store, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = a,
                    onValueChange = { a = it; msg = "" },
                    label = { Text("ID de la autoparte") },
                    leadingIcon = {
                        Icon(Icons.Default.Inventory2, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = q,
                    onValueChange = { q = it; msg = "" },
                    label = { Text("Cantidad") },
                    leadingIcon = {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it; msg = "" },
                    label = { Text("Fecha (DD/MM/AAAA)") },
                    leadingIcon = {
                        Icon(Icons.Default.CalendarMonth, contentDescription = null)
                    },
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
                        val ci = c.toIntOrNull()
                        val vi = v.toIntOrNull()
                        val ai = a.toIntOrNull()
                        val qty = q.toIntOrNull()

                        val cl = clients.find { it.id == ci }
                        val se = sellers.find { it.id == vi }
                        val part = parts.find { it.id == ai }

                        if (
                            cl == null || se == null || part == null ||
                            qty == null || qty <= 0 || date.isBlank()
                        ) {
                            msg = "Revisa los IDs, la cantidad y la fecha."
                        } else if (qty > part.stock) {
                            msg = "Existencia insuficiente."
                        } else {
                            vm.guardar(
                                VentaEntity(
                                    editId,
                                    ci!!,
                                    vi!!,
                                    ai!!,
                                    qty,
                                    part.precio * qty,
                                    date.trim()
                                )
                            )

                            if (editId == 0) {
                                vm.guardar(part.copy(stock = part.stock - qty))
                            }

                            editId = 0
                            c = ""
                            v = ""
                            a = ""
                            q = "1"
                            date = ""
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
                        Icons.Default.PointOfSale,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        if (editId == 0) "Registrar venta" else "Actualizar venta",
                        fontWeight = FontWeight.Bold
                    )
                }

                if (editId != 0) {
                    TextButton(
                        onClick = {
                            editId = 0
                            c = ""
                            v = ""
                            a = ""
                            q = "1"
                            date = ""
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
                "Historial de ventas",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextoOscuro
            )

            Surface(
                color = RojoPrincipal,
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    "${sales.size} ventas",
                    color = Blanco,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (sales.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.PointOfSale,
                        contentDescription = null,
                        tint = TextoSecundario,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Todavía no hay ventas registradas",
                        color = TextoSecundario
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(sales, key = { it.id }) { x ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Blanco),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = RojoPrincipal.copy(alpha = 0.10f)
                                ) {
                                    Icon(
                                        Icons.Default.ShoppingCart,
                                        contentDescription = null,
                                        tint = RojoPrincipal,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        "Venta #${x.id}",
                                        fontWeight = FontWeight.Bold,
                                        color = TextoOscuro
                                    )
                                    Text(
                                        "Fecha: ${x.fecha}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextoSecundario
                                    )
                                }

                                Text(
                                    "$${"%.2f".format(x.total)}",
                                    color = RojoPrincipal,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                "Cliente: ${x.clienteId}  ·  Vendedor: ${x.vendedorId}",
                                color = TextoOscuro,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                "Autoparte: ${x.autoparteId}  ·  Cantidad: ${x.cantidad}",
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
                                        editId = x.id
                                        c = x.clienteId.toString()
                                        v = x.vendedorId.toString()
                                        a = x.autoparteId.toString()
                                        q = x.cantidad.toString()
                                        date = x.fecha
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
                                        vm.borrarVenta(x.id)
                                        if (editId == x.id) {
                                            editId = 0
                                            c = ""
                                            v = ""
                                            a = ""
                                            q = "1"
                                            date = ""
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
