# MotoAmigo

Plataforma de gestión de entregas que conecta emprendedores locales con repartidores independientes.

## Casos de Uso Implementados

### ✅ Registrar Repartidor
Flujo completo de registro en 4 pasos:
- Datos personales (nombre, correo, contraseña, teléfono)
- Documentos (INE, foto de perfil, antecedentes)
- Tipo de transporte y documentos de vehículo
- Cuenta bancaria

### 🔄 En progreso
- Solicitar Pedido
- Registrar Emprendedor
- Aprobar / Rechazar Repartidor

## Arquitectura

Proyecto Maven multi-módulo con 5 capas:

| Módulo | Responsabilidad |
|--------|----------------|
| `MotoAmigoDTO` | Objetos de transferencia de datos |
| `MotoAmigoDominio` | Entidades y enums del negocio |
| `MotoAmigoNegocio` | BOs, convertidores e interfaces |
| `MotoAmigoPersistencia` | DAOs e interfaces |
| `MotoAmigoPresentacion` | Vistas Swing |

## Tecnologías
- Java JDK 17
- Maven (multi-módulo)
- Swing (UI)
- Git/GitHub

## Equipo
Desarrollado para la materia Diseño de Software — ITSON
