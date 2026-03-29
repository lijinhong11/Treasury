# Treasury
A simple economy & points API for Minecraft mods.
Inspired by the Bukkit [Vault](https://github.com/MilkBowl/Vault) plugin.

## Introduction
It is **not an economy implementation**.  
Instead, other mods provide implementations, and Treasury acts as a bridge layer.

## What can it do?
* Access server economy (multi-currency supported)
* Access points / premium currency
* Support multiple economy providers
* Automatically select a primary economy
* Allow mods to interact without depending on specific implementations

## How it works
Treasury defines two main provider types:

* `EconomyProvider` → normal currency
* `PointsProvider` → points / premium currency

Other mods register their implementation, and consumers retrieve them through the API.

## Registry
### Economy
* Stored in `EconomyServiceRegistry`
* Supports multiple providers
* One provider is selected as primary

Selection rules:

1. Using `primaryEconomy` which defined in config if specified
2. Otherwise, use the first registered provider

### Points
* Stored in `PointsServiceRegistry`
* Only one provider allowed

Registering another provider will throw an exception.

## Register Providers
### Economy
```java
Treasury.economy().register(new ExampleEconomyProvider());
```

### Points
```java
Treasury.points().register(new ExamplePointsProvider());
```

Notes:
* Only one points provider allowed
* Please register during mod initialization

### Fabric Entrypoints
Treasury supports register providers via Fabric entrypoints:

* `treasury-economy`
* `treasury-points`

Example:

```json
{
  "entrypoints": {
    "treasury-economy": [
      "com.example.mod.ExampleEconomyProvider"
    ],
    "treasury-points": [
      "com.example.mod.ExamplePointsProvider"
    ]
  }
}
```

## Usage

### Economy
```java
if (Treasury.economy().hasPrimary()) {
    EconomyProvider economy = Treasury.economy().getPrimary();
    double balance = economy.getBalance(playerUuid);
}
```

### Points
```java
if (Treasury.points().isRegistered()) {
    PointsProvider points = Treasury.points().get();
    int balance = points.getBalance(playerUuid);
}
```

## Commands
* `/treasury info`
* `/treasury balance <player>`

Required permission level: `2`

## Requirements
* Fabric: `1.20.1+`
* NeoForge: `1.20.4+`
* Java: `17`

## Build
```bash
./gradlew build
```

## Import
```kotlin
dependencies {
    compileOnly("io.github.lijinhong11:treasury:VERSION")
}
```