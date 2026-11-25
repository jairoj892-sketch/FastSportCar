package com.fastsportcar.app.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fastsportcar.app.data.local.*
import com.fastsportcar.app.data.repository.FastSportCarRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class MainViewModel(private val r:FastSportCarRepository):ViewModel(){
 val autopartes=r.autopartes.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())
 val clientes=r.clientes.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())
 val vendedores=r.vendedores.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())
 val ventas=r.ventas.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())
 fun guardar(x:AutoparteEntity)=viewModelScope.launch{r.guardar(x)}; fun borrarAutoparte(id:Int)=viewModelScope.launch{r.borrarAutoparte(id)}
 fun guardar(x:ClienteEntity)=viewModelScope.launch{r.guardar(x)}; fun borrarCliente(id:Int)=viewModelScope.launch{r.borrarCliente(id)}
 fun guardar(x:VendedorEntity)=viewModelScope.launch{r.guardar(x)}; fun borrarVendedor(id:Int)=viewModelScope.launch{r.borrarVendedor(id)}
 fun guardar(x:VentaEntity)=viewModelScope.launch{r.guardar(x)}; fun borrarVenta(id:Int)=viewModelScope.launch{r.borrarVenta(id)}
}