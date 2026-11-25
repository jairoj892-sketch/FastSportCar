package com.fastsportcar.app.data.repository
import com.fastsportcar.app.data.local.*
class FastSportCarRepository(private val a:AutoparteDao,private val c:ClienteDao,private val v:VendedorDao,private val t:VentaDao){
 val autopartes=a.todas(); val clientes=c.todos(); val vendedores=v.todos(); val ventas=t.todas()
 suspend fun guardar(x:AutoparteEntity){if(x.id==0)a.insertar(x) else a.actualizar(x)}; suspend fun borrarAutoparte(id:Int)=a.eliminar(id)
 suspend fun guardar(x:ClienteEntity){if(x.id==0)c.insertar(x) else c.actualizar(x)}; suspend fun borrarCliente(id:Int)=c.eliminar(id)
 suspend fun guardar(x:VendedorEntity){if(x.id==0)v.insertar(x) else v.actualizar(x)}; suspend fun borrarVendedor(id:Int)=v.eliminar(id)
 suspend fun guardar(x:VentaEntity){if(x.id==0)t.insertar(x) else t.actualizar(x)}; suspend fun borrarVenta(id:Int)=t.eliminar(id)
}