# Description

Lemoncash is a platform that allows users to manage different types of assets.

We want to be able to register users and allow them to make movements in their wallets. For this, an app that exposes a
REST API must be implemented in order to allow these use cases.

It must be taken into account that when registering a user, **three wallets** must be created (three different
currencies) associated to said user with an initial amount of 0 in each one. The currencies are ARS, BTC, USDT.

The significant decimals of each currency must be considered:

- ARS: 2 decimals. Example: 1000.55
- USDT: 2 decimal places.
- BTC: 8 decimal places. Example 0.00005241

# Documentation

[Technical Documentation](./technical-documentation.html).

[User Manual](./user-manual.html).

# Contributors

- Camila Serra - camilaserra5@gmail.com
