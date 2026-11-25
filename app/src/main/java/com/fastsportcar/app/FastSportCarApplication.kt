package com.fastsportcar.app
import android.app.Application
import com.fastsportcar.app.di.AppContainer
class FastSportCarApplication:Application(){ val container:AppContainer by lazy{AppContainer(this)} }