# Seis preguntas para la defensa

1. **¿Qué es Room?** Es una biblioteca de persistencia que facilita el uso de SQLite en Android. Aquí guarda las cuatro tablas localmente.
2. **¿Para qué sirve la arquitectura por capas?** Separa interfaz, estado, lógica de acceso a datos y persistencia para facilitar mantenimiento.
3. **¿Qué hace Repository?** Centraliza las operaciones de lectura, inserción, actualización y eliminación y evita que la interfaz acceda directamente a los DAO.
4. **¿Cómo se aplica la inyección de dependencias?** AppContainer construye Room y el repositorio; la Factory entrega el repositorio al ViewModel.
5. **¿Qué son UDF y Observer?** UDF mantiene un flujo de acciones hacia ViewModel y estado hacia UI. Observer se refleja al observar Flow de Room y actualizar las listas cuando cambian los datos.
6. **¿Cómo funciona CRUD?** Create inserta, Read consulta, Update modifica y Delete elimina registros. Los DAO implementan esas operaciones con Room.
