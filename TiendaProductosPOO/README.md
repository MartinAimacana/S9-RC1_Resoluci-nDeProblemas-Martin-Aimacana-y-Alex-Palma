# Tienda de Productos - Gestión de Inventarios POO

**Integrantes:** Martin Aimacaña y Alex Palma  
**Asignatura:** ISWZ1103 - Programación II  
**Tema:** Tienda de productos, CRUD de productos y gestión de inventarios (comprar y vender).

Proyecto para ISWZ1103 - Programación II. El sistema permite realizar CRUD de productos y gestionar inventario mediante compras y ventas, aplicando encapsulamiento, herencia y polimorfismo.

## Estructura

- `src/modelo`: clases del dominio (`Producto`, `ProductoPerecedero`, `ProductoNoPerecedero`).
- `src/negocio`: lógica de negocio (`Inventario`, validaciones y excepciones).
- `src/interfaz`: interfaz de consola (`Main`) y pruebas (`DemoPruebas`).
- `diagramas`: diagrama UML de clases.
- `evidencias`: capturas de ejecución usadas en el informe.
- `documentacion`: informe técnico en Word y PDF.

## Ejecutar en IntelliJ IDEA

1. Abrir **IntelliJ IDEA**.
2. Seleccionar **Open**.
3. Elegir la carpeta principal `TiendaProductosPOO`.
4. Abrir el archivo `src/interfaz/Main.java`.
5. Presionar el botón verde **Run** junto al método `main`.
6. En la consola se muestra el encabezado con los integrantes del grupo: Martin Aimacaña y Alex Palma.

Para ejecutar las pruebas automáticas en IntelliJ IDEA, abrir `src/interfaz/DemoPruebas.java` y ejecutar su método `main`.

## Compilar y ejecutar por terminal

En Windows, Linux o Mac, desde la carpeta principal:

```bash
javac -encoding UTF-8 -d bin src/modelo/*.java src/negocio/*.java src/interfaz/*.java
java -cp bin interfaz.Main
```

Para ejecutar las pruebas automáticas:

```bash
java -cp bin interfaz.DemoPruebas
```

## Repositorio GitHub

1. Crear un repositorio público en GitHub.
2. Subir todos los archivos de esta carpeta.
3. Copiar el enlace público y pegarlo en el informe técnico donde dice: "Enlace de GitHub".


## Informe final incluido

El informe con el formato corregido se encuentra en:

`documentacion/INFORME_FINAL_FORMATO_CORRECTO_Martin_Aimacana_Alex_Palma.pdf`

También se reemplazaron las versiones PDF principales de la carpeta `documentacion` para evitar confusiones al entregar el proyecto.
