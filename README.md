# Tarea-3---Paradigma-funcional-y-l-gico
🍽️ Sistema de Gestión de Calorías - Restaurante "Mi Mejor Comida"
Este programa en Java permite gestionar un menú de restaurante simulando el cálculo de calorías y la búsqueda de combinaciones de platos bajos en calorías. Desarrollado usando elementos de programación funcional con Streams, List.of, record, y expresiones lambda de Java.

📋 Características
✅ Calcular las calorías totales de un menú personalizado (entrada, plato principal y postre).

✅ Listar combinaciones de menú que no sobrepasen un límite calórico definido por el usuario.

✅ Uso de estructuras funcionales (Streams, map, filter, collect) y programación moderna en Java (JDK 17+ recomendado).

🧑‍💻 Cómo usar
1. Compilar el proyecto
bash
Copiar
Editar
javac Main.java
2. Ejecutar el programa
bash
Copiar
Editar
java Main
🗂️ Menú principal
Al iniciar, se muestra un menú con las siguientes opciones:

markdown
Copiar
Editar
1. Calcular calorías de un menú
2. Ver combinaciones bajas en calorías
3. Salir
Opción 1: Calcular calorías de un menú
Permite seleccionar:

Una entrada

Un plato principal

Un postre
El sistema calcula el total de calorías de la combinación seleccionada.

Opción 2: Ver combinaciones bajas en calorías
El usuario ingresa un límite máximo de calorías y el sistema muestra todas las combinaciones de menús posibles que cumplan ese criterio.

🧠 Estructura del proyecto
java
Copiar
Editar
record Plato(String nombre, int calorias);
Cada Plato representa un ítem del menú con nombre y valor calórico.

Las listas ENTRADAS, PRINCIPALES, y POSTRES son inmutables y contienen los datos base del restaurante.

Se utiliza programación funcional para generar combinaciones y filtrar por calorías de forma eficiente.

📦 Requisitos
Java 17 o superior (por uso de record y List.of)

Terminal o IDE compatible con compilación y ejecución Java

👥 Integrantes

Javier Silva

Felipe Arancia

Franco Labarca (alias Karl Marx)
