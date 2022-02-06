# Description
Lemoncash es una plataforma que permite a los usuarios administrar distintos tipos de activos.

Queremos poder dar de alta usuarios y permitir que los mismos puedan realizar movimientos en sus billeteras. Para esto, se debe implementar una app que exponga una API REST para poder permitir estos casos de uso.

Se debe tener en cuenta que al dar de alta un usuario, se deben crear **tres billeteras** (tres
monedas diferentes) asociadas a dicho usuario con un monto inicial de 0 en cada una.
Las monedas son ARS, BTC, USDT.

Se debe contemplar los decimales significativos de cada moneda:

- ARS: 2 decimales. Ejemplo: 1000.55
- USDT: 2 decimales.
- BTC: 8 decimales. Ejemplo 0.00005241

# Documentation
[Technical Documentation](./technical-documentation.html).

[User Manual](./user-manual.html).

# Contributors
- Camila Serra - camilaserra5@gmail.com
