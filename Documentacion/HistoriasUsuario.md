# RUMBO JOVEN
## Historias de usuario

**Asignatura:** Programación 1  
**Estudiante:** Escribe aquí tu nombre

### HU01. Registrar cliente

**Como** vendedor de la agencia,  
**quiero** registrar el nombre, la cédula, la fecha del viaje y el destino,  
**para** guardar la información del cliente.

**Criterios de aceptación:**
- El sistema debe solicitar los datos del cliente.
- Los campos obligatorios no deben quedar vacíos.
- El sistema debe confirmar cuando el cliente se registre correctamente.

**Prioridad:** Alta.

### HU02. Seleccionar tipo de maleta

**Como** vendedor,  
**quiero** seleccionar una maleta básica o premium,  
**para** establecer el precio que se utilizará en el cálculo.

**Criterios de aceptación:**
- La maleta básica (MB) debe costar $285.325 COP.
- La maleta premium (MP) debe costar $325.650 COP.
- El sistema debe informar si se ingresa un tipo de maleta inválido.

**Prioridad:** Alta.

### HU03. Registrar acompañantes

**Como** vendedor,  
**quiero** ingresar la cantidad de acompañantes,  
**para** calcular el valor del viaje.

**Criterios de aceptación:**
- La cantidad debe ser un número entero.
- No se deben aceptar cantidades negativas.
- El sistema debe informar si el dato ingresado no es válido.

**Prioridad:** Alta.

### HU04. Calcular el valor del viaje

**Como** vendedor,  
**quiero** calcular el valor del viaje según la maleta y los acompañantes,  
**para** conocer el valor correspondiente.

**Criterios de aceptación:**
- El sistema debe utilizar el precio de la maleta seleccionada.
- El cálculo debe multiplicar el precio por la cantidad de acompañantes, según el caso de estudio.
- El sistema debe mostrar el resultado en pesos colombianos.

**Prioridad:** Alta.

### HU05. Visualizar información

**Como** vendedor,  
**quiero** consultar los datos registrados,  
**para** verificar la información de los clientes.

**Criterios de aceptación:**
- El sistema debe mostrar los clientes registrados.
- Debe mostrar los datos del viaje y el valor calculado cuando estén disponibles.
- Si no existen registros, debe informar al usuario.

**Prioridad:** Media.

### HU06. Generar respaldo TXT

**Como** vendedor,  
**quiero** guardar la información en un archivo de texto,  
**para** conservar una copia de los registros.

**Criterios de aceptación:**
- El sistema debe crear un archivo TXT.
- El archivo debe contener los datos de los clientes registrados.
- El sistema debe informar si el respaldo se creó correctamente o si ocurrió un error.

**Prioridad:** Media.

### HU07. Salir del programa

**Como** usuario,  
**quiero** salir del programa mediante la opción correspondiente,  
**para** finalizar su utilización de forma sencilla.

**Criterios de aceptación:**
- La opción 7 debe finalizar el programa.
- El sistema debe mostrar un mensaje de despedida.

**Prioridad:** Baja.