# Analizador-de-Cadenas
Proyecto de analizador de cadenas diseñado para procesar y validar cadenas de texto según reglas específicas. Este analizador utiliza expresiones regulares y métodos personalizados para verificar patrones, analizar la estructura de las cadenas e identificar coincidencias o errores de formato.

# Documentación Técnica - Analizador de Cadenas

## Descripción General
El Analizador de Cadenas es una aplicación Java con interfaz gráfica que permite validar cadenas de texto según un conjunto específico de reglas. La aplicación puede analizar tanto cadenas numéricas como texto, verificando su formato y estructura.

## Estructura del Código

### Clase Principal: `AnalizadorCadenas`
Extiende de `JFrame` para proporcionar la interfaz gráfica de usuario.

#### Atributos Principales
- `txtEntrada`: Campo de texto (JTextField) donde el usuario ingresa la cadena a analizar
- `txtResultado`: Área de texto (JTextArea) donde se muestran los resultados del análisis
- `btnAnalizar`: Botón para iniciar el análisis
- `btnLimpiar`: Botón para limpiar los campos de entrada y resultado

### Constructor
```java
public AnalizadorCadenas()
```
Inicializa la interfaz gráfica con los siguientes elementos:
- Configura el título, tamaño y ubicación de la ventana
- Inicializa FlatLaf como tema visual
- Crea y organiza los componentes de la interfaz
- Configura los listeners para los botones

### Métodos Principales

#### `iniciadorFlatlaf()`
```java
private void iniciadorFlatlaf()
```
- **Propósito**: Inicializa el tema visual FlatLightLaf para mejorar la apariencia de la aplicación
- **Comportamiento**: Intenta establecer el Look and Feel, capturando cualquier excepción que pueda ocurrir

#### `limpiarCampos()`
```java
private void limpiarCampos()
```
- **Propósito**: Limpia todos los campos de la interfaz
- **Acciones**:
  - Limpia el campo de entrada
  - Limpia el área de resultados
  - Establece el foco en el campo de entrada

#### `analizarCadena()`
```java
private void analizarCadena()
```
- **Propósito**: Método principal que realiza el análisis de la cadena ingresada
- **Proceso de análisis**:
  1. Obtiene la cadena del campo de entrada
  2. Verifica si está vacía
  3. Verifica si termina en punto
  4. Analiza si es numérica o texto
  5. Verifica caracteres especiales
  6. Realiza validaciones específicas según el tipo de cadena

##### Validaciones para Cadenas Numéricas:
- Debe contener solo dígitos
- Puede tener una única coma decimal
- La coma no puede estar al inicio o final
- Debe haber dígitos antes y después de la coma

##### Validaciones para Cadenas de Texto:
- Primera letra debe ser mayúscula
- Resto de letras deben ser minúsculas
- Reglas específicas para consonantes y vocales repetidas:
  - Se permiten 'll' y 'cc'
  - Se permite 'oo'
  - No se permiten otras repeticiones

#### `esVocal(char c)`
```java
private boolean esVocal(char c)
```
- **Propósito**: Determina si un carácter es una vocal
- **Parámetros**: `c` - carácter a evaluar
- **Retorno**: `true` si es vocal, `false` si no lo es
- **Comportamiento**: Convierte el carácter a minúscula y verifica si es a, e, i, o, u

#### `esConsonante(char c)`
```java
private boolean esConsonante(char c)
```
- **Propósito**: Determina si un carácter es una consonante
- **Parámetros**: `c` - carácter a evaluar
- **Retorno**: `true` si es consonante, `false` si no lo es
- **Comportamiento**: Verifica que sea una letra y no sea vocal

#### `mostrarResultado(String mensaje)`
```java
private void mostrarResultado(String mensaje)
```
- **Propósito**: Muestra el resultado del análisis en el área de texto
- **Parámetros**: `mensaje` - resultado del análisis
- **Comportamiento**: Actualiza el contenido del área de texto con el mensaje

### Método Principal
```java
public static void main(String[] args)
```
- **Propósito**: Punto de entrada de la aplicación
- **Comportamiento**: 
  - Utiliza `SwingUtilities.invokeLater()` para asegurar que la interfaz se cree en el EDT (Event Dispatch Thread)
  - Crea y muestra una instancia de AnalizadorCadenas

## Reglas de Validación

### Para Todas las Cadenas
1. No pueden estar vacías
2. Deben terminar en punto
3. No pueden contener caracteres especiales no permitidos

### Para Cadenas Numéricas
1. Solo pueden contener dígitos y máximo una coma decimal
2. La coma debe estar entre dígitos

### Para Cadenas de Texto
1. Deben comenzar con mayúscula
2. El resto de letras deben ser minúsculas
3. Solo se permiten letras, espacios y los caracteres ',', ';', ':'
4. Reglas especiales para repeticiones:
   - Se permiten 'll' y 'cc'
   - Se permite 'oo'
   - No se permiten otras repeticiones de vocales o consonantes

## Consideraciones Técnicas
- La aplicación utiliza Java Swing para la interfaz gráfica
- Implementa FlatLaf para mejorar la apariencia visual
- Utiliza programación orientada a eventos para manejar las acciones del usuario
- Implementa manejo de errores y validaciones exhaustivas
- La interfaz es responsiva y proporciona feedback inmediato al usuario
