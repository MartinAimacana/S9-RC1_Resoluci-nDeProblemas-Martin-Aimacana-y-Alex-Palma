# Sistema de Gestión de Inventarios - Tienda de Productos

Proyecto realizado para la materia **ISWZ1103 - Programación II**.

## Integrantes

- Martin Aimacaña
- Alex Palma

## Tema del proyecto

**Tienda de productos: CRUD de productos y gestión de inventarios**

El sistema permite administrar productos de una tienda pequeña, controlando el registro, consulta, actualización, eliminación, compras y ventas de productos.

## Descripción

Este proyecto fue desarrollado en **Java** aplicando conceptos de **Programación Orientada a Objetos (POO)**.

La idea principal es ayudar a una tienda a manejar su inventario de una forma más ordenada, evitando errores comunes como vender productos sin stock, registrar datos incompletos o perder el control de productos vencidos.

El sistema trabaja con productos perecederos y no perecederos, permitiendo diferenciar su comportamiento dentro del inventario.

## Funcionalidades principales

- Registrar productos.
- Consultar productos del inventario.
- Actualizar datos de un producto.
- Eliminar productos.
- Comprar productos y aumentar stock.
- Vender productos y reducir stock.
- Validar datos obligatorios.
- Controlar presupuesto disponible.
- Mostrar alertas de bajo stock.
- Detectar productos perecederos vencidos.

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- Programación Orientada a Objetos
- UML
- Draw.io / Graphviz
- GitHub

## Conceptos de POO aplicados

### Encapsulamiento

Los atributos de las clases se manejan de forma privada y se accede a ellos mediante métodos.

### Herencia

Se utiliza una clase base llamada `Producto`, de la cual heredan:

- `ProductoPerecedero`
- `ProductoNoPerecedero`

### Polimorfismo

Los productos pueden comportarse de diferente manera según su tipo, por ejemplo al verificar si están vencidos o al mostrar su tipo.

## Estructura del proyecto

```text
TiendaProductosPOO/
│
├── src/
│   ├── modelo/
│   │   ├── Producto.java
│   │   ├── ProductoPerecedero.java
│   │   └── ProductoNoPerecedero.java
│   │
│   ├── negocio/
│   │   ├── Inventario.java
│   │   ├── Validador.java
│   │   └── ValidacionException.java
│   │
│   └── interfaz/
│       ├── Main.java
│       └── DemoPruebas.java
│
├── diagramas/
│   └── diagrama_clases_uml.png
│
├── documentacion/
│   └── informe_final.pdf
│
├── evidencias/
│   └── capturas/
│
└── README.md
```

## Cómo ejecutar el proyecto en IntelliJ IDEA

1. Descargar o clonar este repositorio.
2. Abrir **IntelliJ IDEA**.
3. Seleccionar la opción **Open**.
4. Abrir la carpeta del proyecto `TiendaProductosPOO`.
5. Ir a la ruta:

```text
src/interfaz/Main.java
```

6. Ejecutar el archivo `Main.java` dando clic en el botón verde de **Run**.

También se puede ejecutar el archivo:

```text
src/interfaz/DemoPruebas.java
```

para revisar los casos de prueba automáticos.

## Casos de prueba incluidos

El proyecto incluye pruebas para verificar:

- Registro de producto con datos vacíos.
- Registro correcto de producto perecedero.
- Listado del inventario.
- Compra de productos.
- Venta con stock insuficiente.
- Actualización de productos.
- Eliminación de productos.
- Alertas de bajo stock y vencimiento.

## Diagrama UML

El proyecto incluye un diagrama UML donde se muestra la relación entre las clases principales del sistema, como `Producto`, `ProductoPerecedero`, `ProductoNoPerecedero`, `Inventario`, `Validador` y la interfaz de usuario.

## Conclusión

Este proyecto permite aplicar de forma práctica los conceptos de Programación Orientada a Objetos en un caso real de gestión de inventarios.

La solución ayuda a controlar productos, stock, compras, ventas, presupuesto y alertas, cumpliendo con los requerimientos planteados para la actividad.

## Estado del proyecto

Proyecto finalizado para entrega académica.
