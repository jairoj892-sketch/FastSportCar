package com.fastsportcar.app.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fastsportcar.app.data.repository.FastSportCarRepository
class MainViewModelFactory(private val r:FastSportCarRepository):ViewModelProvider.Factory{@Suppress("UNCHECKED_CAST") override fun <T:ViewModel> create(c:Class<T>):T{if(c.isAssignableFrom(MainViewModel::class.java))return MainViewModel(r) as T; throw IllegalArgumentException("ViewModel desconocido")}}