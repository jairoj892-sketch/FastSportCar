package com.fastsportcar.app.data.local
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities=[AutoparteEntity::class,ClienteEntity::class,VendedorEntity::class,VentaEntity::class],version=1,exportSchema=false)
abstract class AppDatabase:RoomDatabase(){ abstract fun autoparteDao():AutoparteDao; abstract fun clienteDao():ClienteDao; abstract fun vendedorDao():VendedorDao; abstract fun ventaDao():VentaDao }