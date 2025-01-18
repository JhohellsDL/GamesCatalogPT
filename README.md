# Catálogo de Videojuegos 🎮

Este proyecto es una aplicación de Android que presenta un catálogo de videojuegos interactivo, desarrollado como parte de una prueba técnica. Los usuarios pueden buscar, marcar juegos como favoritos y descubrir recomendaciones aleatorias.

## Características Técnicas ✨

### Arquitectura 🏗️
- **MVVM (Model-View-ViewModel)**: Separa la lógica de presentación, datos y la interfaz gráfica para mejorar la escalabilidad y mantenibilidad.
- **Unidirectional Data Flow (UDF)**: La interacción del usuario actualiza el estado de la vista a través de `StateFlow` y `LiveData`.

### Desarrollo de Interfaz 🎨
- **Jetpack Compose**: Se utilizó para construir una UI moderna y declarativa. Incluye:
  - `LazyColumn` para listas eficientes.
  - Soporte para temas claro/oscuro.
  - Componentes reutilizables como tarjetas (`Card`) con esquinas redondeadas y campos de búsqueda.

### Gestión de Estado ⚙️
- **Kotlin Flow**: Para la reactividad en los datos y la sincronización de la UI.
- **MutableStateFlow**: Se utiliza para gestionar el estado de búsqueda y el filtrado dinámico.

### Navegación 🚦
- **Navigation Component**: Navegación entre pantallas implementada con `NavController` y compatible con Compose.
- **AppNavigator**: Clase personalizada para manejar navegación específica y modular.

### Inyección de Dependencias 🧩
- **Koin**: Framework ligero para la inyección de dependencias, simplificando la inicialización de ViewModels y repositorios.

### Acceso a Datos 🌐
- **API REST**: Datos obtenidos de [FreeToGame API](https://www.freetogame.com/api/games).
- **Retrofit**: Cliente HTTP para realizar peticiones a la API.
- **Room Database** (futuro): Planificado para almacenamiento local y funcionalidad offline.

### Funcionalidad Implementada 🚀
1. **Listado de Juegos**:
   - Juegos obtenidos de la API.
   - Mostrados en una lista desplazable con `LazyColumn`.
2. **Favoritos**:
   - Los usuarios pueden marcar juegos como favoritos.
   - Los favoritos se almacenan usando un `Set<Int>` para identificación rápida.
3. **Búsqueda**:
   - Filtra juegos dinámicamente por título o descripción.
   - Implementada con `StateFlow` y `TextField`.
4. **Recomendación Aleatoria**:
   - Función para mostrar un juego seleccionado al azar.
5. **Detalle de Juegos**:
   - Navegación a una pantalla detallada al hacer clic en un juego.


## Pruebas Realizadas 🧪

- **Unidad**:
  - Pruebas de filtrado de búsqueda.
  - Pruebas de funcionalidad de favoritos.
- **Instrumentación**:
  - Validación de navegación entre pantallas.
- **Manual**:
  - Verificación de estados vacíos para búsqueda y favoritos.
  - Validación de cambios dinámicos en la UI.

## Características ✨

- **Listado de Juegos**: Visualiza una lista de videojuegos con sus detalles.
- **Búsqueda**: Filtra juegos por título o descripción.
- **Favoritos**: Marca juegos como favoritos para un acceso rápido.
- **Juego Aleatorio**: Obtén una recomendación aleatoria de un juego.
- **Interfaz Moderna**: UI desarrollada con Jetpack Compose.
- **Navegación Intuitiva**: Navega a los detalles de un juego con facilidad.
  
## Tecnologías Utilizadas 🛠️

- **Kotlin**: Lenguaje principal de desarrollo.
- **Jetpack Compose**: Framework moderno para diseño de interfaces.
- **MVVM (Model-View-ViewModel)**: Patrón de arquitectura.
- **Koin**: Inyección de dependencias.
- **API REST**: Datos obtenidos de [FreeToGame API](https://www.freetogame.com/api/games).
- **Coroutines y Flow**: Para manejo asíncrono y reactividad.
  
## Capturas de Pantalla 📸
<img width="413" alt="Captura de pantalla 2025-01-18 a la(s) 07 15 40" src="https://github.com/user-attachments/assets/3451fe30-738c-452e-80fa-269338fddee7" />
<img width="420" alt="Captura de pantalla 2025-01-18 a la(s) 07 16 04" src="https://github.com/user-attachments/assets/08e5ab7c-b7e4-4eff-bf84-60cdd070e336" />
<img width="415" alt="Captura de pantalla 2025-01-18 a la(s) 07 16 14" src="https://github.com/user-attachments/assets/4e66fe27-5644-4487-adc7-a3ab15a02427" />
<img width="407" alt="Captura de pantalla 2025-01-18 a la(s) 07 16 35" src="https://github.com/user-attachments/assets/62c27bdd-f642-435f-b567-ac3adbd8993d" />



