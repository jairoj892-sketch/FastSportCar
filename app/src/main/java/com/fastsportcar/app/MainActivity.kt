
package com.fastsportcar.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.fastsportcar.app.pantallas.AutopartesScreen
import com.fastsportcar.app.pantallas.ClientesScreen
import com.fastsportcar.app.pantallas.HomeScreen
import com.fastsportcar.app.pantallas.LoginScreen
import com.fastsportcar.app.pantallas.PrivacyScreen
import com.fastsportcar.app.pantallas.VendedoresScreen
import com.fastsportcar.app.pantallas.VentasScreen
import com.fastsportcar.app.pantallas.WelcomeScreen
import com.fastsportcar.app.ui.theme.FastSportCarTheme
import com.fastsportcar.app.viewmodel.MainViewModel
import com.fastsportcar.app.viewmodel.MainViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as FastSportCarApplication

        val vm = ViewModelProvider(
            this,
            MainViewModelFactory(app.container.repository)
        )[MainViewModel::class.java]

        setContent {
            FastSportCarTheme {
                FastSportCarApp(vm)
            }
        }
    }
}

private enum class Page {
    WELCOME,
    LOGIN,
    HOME,
    PRIVACY,
    AUTOPARTES,
    CLIENTES,
    VENDEDORES,
    VENTAS
}

@Composable
fun FastSportCarApp(vm: MainViewModel) {

    var page by rememberSaveable {
        mutableStateOf(Page.WELCOME.name)
    }

    var error by rememberSaveable {
        mutableStateOf("")
    }

    val currentPage = Page.valueOf(page)

    BackHandler(currentPage != Page.WELCOME) {
        page = when (currentPage) {
            Page.LOGIN, Page.PRIVACY -> Page.WELCOME.name
            else -> Page.HOME.name
        }
    }

    when (currentPage) {

        Page.WELCOME -> WelcomeScreen(
            {
                page = Page.LOGIN.name
            },
            {
                page = Page.PRIVACY.name
            }
        )

        Page.LOGIN -> LoginScreen(
            error,
            {
                page = Page.WELCOME.name
            }
        ) { email, password ->
            if (email.contains("@") && password.length >= 4) {
                error = ""
                page = Page.HOME.name
            } else {
                error = "Correo válido y contraseña de mínimo 4 caracteres."
            }
        }

        Page.HOME -> HomeScreen(
            {
                page = Page.PRIVACY.name
            },
            {
                page = Page.AUTOPARTES.name
            },
            {
                page = Page.CLIENTES.name
            },
            {
                page = Page.VENDEDORES.name
            },
            {
                page = Page.VENTAS.name
            },
            {
                page = Page.WELCOME.name
            }
        )

        Page.PRIVACY -> PrivacyScreen {
            page = Page.WELCOME.name
        }

        Page.AUTOPARTES -> AutopartesScreen(vm) {
            page = Page.HOME.name
        }

        Page.CLIENTES -> ClientesScreen(vm) {
            page = Page.HOME.name
        }

        Page.VENDEDORES -> VendedoresScreen(vm) {
            page = Page.HOME.name
        }

        Page.VENTAS -> VentasScreen(vm) {
            page = Page.HOME.name
        }
    }
}
