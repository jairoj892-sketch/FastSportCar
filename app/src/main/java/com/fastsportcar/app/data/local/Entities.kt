package com.fastsportcar.app.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName="autopartes") data class AutoparteEntity(@PrimaryKey(autoGenerate=true) val id:Int=0,val nombre:String,val marca:String,val modelo:String,val descripcion:String,val precio:Double,val stock:Int)
@Entity(tableName="clientes") data class ClienteEntity(@PrimaryKey(autoGenerate=true) val id:Int=0,val nombre:String,val telefono:String,val correo:String)
@Entity(tableName="vendedores") data class VendedorEntity(@PrimaryKey(autoGenerate=true) val id:Int=0,val nombre:String,val telefono:String,val correo:String)
@Entity(tableName="ventas") data class VentaEntity(@PrimaryKey(autoGenerate=true) val id:Int=0,val clienteId:Int,val vendedorId:Int,val autoparteId:Int,val cantidad:Int,val total:Double,val fecha:String)