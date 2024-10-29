# Analizador de Cadenas

## Descripción General
El Analizador de Cadenas es una aplicación Java con interfaz gráfica que permite validar cadenas de texto según un conjunto específico de reglas. La aplicación puede analizar tanto cadenas numéricas como texto, verificando su formato y estructura. Utiliza un tema oscuro moderno con FlatLaf y una interfaz de usuario mejorada con efectos visuales.

## Estructura del Código

### Clase Principal: AnalizadorCadenas
Extiende de JFrame para proporcionar la interfaz gráfica de usuario.

### Atributos Principales
- `txtEntrada`: Campo de texto (JTextField) donde el usuario ingresa la cadena a analizar
- `txtResultado`: Área de texto (JTextArea) donde se muestran los resultados del análisis
- `btnAnalizar`: Botón para iniciar el análisis
- `btnLimpiar`: Botón para limpiar los campos
- `btnSalir`: Botón para salir de la aplicación

### Constructor
```java
public AnalizadorCadenas()
```
Inicializa la interfaz gráfica con los siguientes elementos:
- Configura el título, tamaño (600x400) y ubicación de la ventana
- Inicializa FlatDarkLaf como tema visual oscuro
- Crea y organiza los componentes de la interfaz con un diseño moderno
- Configura los listeners para los botones
- Implementa un diseño con padding y espaciado consistente

### Métodos Principales

#### iniciadorFlatlaf()
```java
private void iniciadorFlatlaf()
```
- Propósito: Inicializa el tema visual FlatDarkLaf para una apariencia moderna y oscura
- Comportamiento: Intenta establecer el Look and Feel oscuro, capturando cualquier excepción

#### configurarBoton(JButton boton)
```java
private void configurarBoton(JButton boton)
```
- Propósito: Configura el estilo visual de los botones
- Características:
  - Dimensiones: 100x30 píxeles
  - Colores: 
    - Fondo: RGB(60, 63, 65)
    - Texto: Blanco
    - Borde: Gris
  - Bordes redondeados con radio de 6 píxeles
  - Padding interno de 4px vertical y 8px horizontal
  - Efecto hover que cambia el color de fondo y borde
  - Utiliza el estilo "roundRect" de FlatLaf
  - Incluye efectos de transición al pasar el mouse

#### limpiarCampos()
```java
private void limpiarCampos()
```
- Propósito: Limpia todos los campos de la interfaz
- Acciones:
  - Limpia el campo de entrada
  - Limpia el área de resultados
  - Establece el foco en el campo de entrada

#### analizarCadena()
```java
private void analizarCadena()
```
[El resto de la documentación del método analizarCadena() y los métodos auxiliares permanece igual]

## Interfaz de Usuario

### Diseño Visual
- Tema oscuro moderno usando FlatDarkLaf
- Área de resultados con fondo oscuro (RGB(45, 45, 45)) y texto blanco
- Botones con diseño moderno:
  - Esquinas redondeadas
  - Efectos hover
  - Bordes definidos pero sutiles
  - Padding interno para mejor legibilidad

### Componentes Principales
- Panel de entrada en la parte superior
- Área de resultados en el centro con scroll
- Panel de botones a la derecha con disposición vertical
- Márgenes y espaciado consistentes (10px)

## Consideraciones Técnicas
- La aplicación utiliza Java Swing para la interfaz gráfica
- Implementa FlatDarkLaf para una apariencia moderna y oscura
- Utiliza programación orientada a eventos para manejar las acciones del usuario
- Implementa manejo de errores y validaciones exhaustivas
- La interfaz es responsiva y proporciona feedback visual inmediato
- Los componentes visuales incluyen efectos de transición y estados hover
- Utiliza BorderFactory para crear bordes compuestos y padding

## Reglas de Validación
[Las reglas de validación permanecen iguales a la documentación original]

### Para Todas las Cadenas
- No pueden estar vacías
- Deben terminar en punto
- No pueden contener caracteres especiales no permitidos

### Para Cadenas Numéricas
- Solo pueden contener dígitos y máximo una coma decimal
- La coma debe estar entre dígitos

### Para Cadenas de Texto
- Deben comenzar con mayúscula
- El resto de letras deben ser minúsculas
- Solo se permiten letras, espacios y los caracteres ',', ';', ':'
- Reglas especiales para repeticiones:
  - Se permiten 'll' y 'cc'
  - Se permite 'oo'
  - No se permiten otras repeticiones de vocales o consonantes
