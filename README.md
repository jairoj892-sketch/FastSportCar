# FastSportCar — Unidad 3

App Android para administrar autopartes, clientes, vendedores y ventas. Incluye bienvenida, login demostrativo, panel, cuatro módulos de datos y aviso de privacidad.

## Arquitectura
- Jetpack Compose: interfaz.
- ViewModel: estado y acciones.
- Repository: acceso a datos.
- Room/SQLite: persistencia local.
- AppContainer: inyección de dependencias.
- Application + AppContainer: instancia Singleton de base de datos/repositorio.
- Flow observado con collectAsStateWithLifecycle: Observer.
- Acciones hacia ViewModel y estado hacia UI: UDF.

## Configuración
AGP 9.0.1 (Kotlin integrado 2.2.10), Gradle 9.1.0, KSP 2.2.10-2.0.2, Room 2.7.0, JDK 25 para ejecutar Gradle, bytecode Java 17, compileSdk 35 y minSdk 26.

## Ejecutar
1. Extrae el ZIP.
2. Abre la carpeta `FastSportCar_Completo` en Android Studio.
3. Espera Gradle Sync (la primera vez necesita Internet para descargar Gradle y dependencias).
4. Ejecuta en un AVD o dispositivo Android con Run.

## Prueba
El acceso es demostrativo: cualquier correo con @ y contraseña de 4 caracteres o más. Registra primero una autoparte, un cliente y un vendedor. Usa los IDs mostrados en sus listas para registrar una venta. La venta calcula el total y descuenta stock. Prueba editar y eliminar en los módulos de autopartes, clientes y vendedores.

## Alcance
Las tablas son autopartes, clientes, vendedores y ventas. CRUD implementado en los cuatro módulos: insertar, consultar, editar y eliminar. En ventas, la edición actualiza el registro; el ajuste automático de existencias se aplica al crear una venta nueva. Los IDs de relación se introducen manualmente. No hay autenticación real, sincronización remota ni pagos. Usa datos ficticios durante la evaluación.
