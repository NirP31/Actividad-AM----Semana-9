Actividad AM – Semana 9
----------------------------------------------------------------------------------------

Este proyecto es una aplicación Android (Kotlin, API 24+) que implementa varias funcionalidades de práctica semanal: contador de visitas, modo claro/oscuro, gestión de perfil de usuario y almacenamiento con 
SharedPreferences. A continuación se detalla de qué trata y cómo crearlo/personalizarlo paso a paso.

Descripción
-------------------
La app permite:

-Contador de visitas: cada vez que se abre la pantalla principal, incrementa y muestra el número de visitas; incluye botón para resetearlo.
-Modo claro/oscuro: un Switch que alterna entre temas, persiste la preferencia y aplica cambio en tiempo real.
-Perfil de usuario: Activity separada donde el usuario ingresa Nombre, Edad y Email; botones de “Guardar” y “Cargar” y opción de “Volver”.
-Gestión con SharedPreferences: toda la persistencia de datos (contador, tema, perfil, username de ejemplo) se maneja con un helper personalizado.

Proceso de creación
-------------------
1. Definición de objetivos y requisitos

Saber parte de la necesidad de implementar varias funcionalidades prácticas: un contador de visitas, un modo claro/oscuro con persistencia, y una pantalla de perfil de usuario que permita guardar 
y recuperar datos (nombre, edad, email). Se revisa la especificación de la actividad para acordar qué componentes y flujos debe incluir la aplicación.

2. Configuración del entorno

-Abrir Android Studio (versión que soporte Kotlin DSL y API mínimo 24).
-Verificar que el SDK y las herramientas estén actualizados para Android 7.0+.
-Configurar control de versiones Git en el proyecto local, crear un repositorio vacío en GitHub y enlazarlo.

3. Creación del proyecto

-Crear un nuevo proyecto con plantilla “Empty Activity” o “Empty Views Activity”, estableciendo el paquete (com.example.actividadam_semana9), lenguaje Kotlin y Gradle en Kotlin DSL.
-Habilitar ViewBinding en la configuración de Gradle para facilitar la referencia a vistas sin usar findViewById manual.

4. Estructura de carpetas y archivos básicos

-Verificar la existencia de los archivos Gradle en la raíz y en el módulo app.
-Asegurar la configuración de versiones de compileSdk, minSdk, targetSdk, y las dependencias esenciales (AndroidX Core, AppCompat, Material Components).
-Crear o revisar archivos de recursos: layouts, valores de colores, cadenas, temas claro y oscuro.

5. Diseño de la interfaz de usuario

-En el layout principal (activity_main.xml), agregar los elementos necesarios: campo de entrada para usuario de ejemplo, botones de guardar/cargar/limpiar, botón para abrir perfil, switch para 
modo oscuro, indicador de contador y botón para resetearlo.
-En el layout de perfil (activity_perfil.xml), incluir campos para nombre, edad y email, botones de guardar, cargar datos y volver a la pantalla anterior.

6. Implementación de la lógica de persistencia

-Crear o adaptar un helper de SharedPreferences que maneje lectura y escritura de diferentes tipos (String, Boolean, Int, etc.).
-Definir claves claras para cada dato: contador, tema, perfil (nombre/edad/email) y otros campos (por ejemplo, indicador de “primera vez”).
-En la actividad principal, al iniciar, leer las preferencias de tema y contador; al interactuar con switch o botones, actualizar la preferencia correspondiente.
-En la actividad de perfil, capturar y validar la entrada de usuario, guardar en SharedPreferences y permitir cargar valores existentes para mostrar o editar.

7. Temas y apariencia (modo claro/oscuro)

-Configurar dos archivos de tema: uno en valores normales y otro en valores-night, asegurando que los atributos de fondo y texto cambien según el modo.
-Usar un tema basado en MaterialComponents DayNight para aprovechar cambios automáticos de estilos.
-En el código, antes o justo al crear la vista, aplicar el modo guardado usando AppCompatDelegate para que la interfaz refleje inmediatamente el tema elegido.

8. Navegación entre pantallas

-Registrar las actividades en el manifiesto.
-En la actividad principal, enlazar un botón que lance la pantalla de perfil.
-En la actividad de perfil, colocar un botón “Volver” que cierre la pantalla y regrese al anterior.

9. Pruebas y ajustes

-Ejecutar la app en emulador o dispositivo real: verificar que al abrir se muestra mensaje de bienvenida la primera vez, el contador incrementa correctamente, el switch cambia el tema en tiempo 
real y persiste, guardar/cargar perfil funciona, y el botón “Volver” regresa adecuadamente.
-Ajustar márgenes, paddings y comportamiento de scroll para dispositivos con diferentes tamaños de pantalla.
-Revisar insets si se usa Edge-to-Edge para que el contenido se muestre correctamente detrás de barras de sistema.
