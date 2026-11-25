package com.fastsportcar.app.di
import android.content.Context
import androidx.room.Room
import com.fastsportcar.app.data.local.AppDatabase
import com.fastsportcar.app.data.repository.FastSportCarRepository
class AppContainer(context:Context){
 // Singleton: una instancia de Room para toda la aplicación.
 private val db=Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"fastsportcar_database").build()
 val repository=FastSportCarRepository(db.autoparteDao(),db.clienteDao(),db.vendedorDao(),db.ventaDao())
}