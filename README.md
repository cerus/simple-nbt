

[![badge](https://img.shields.io/badge/jitpack.io-v1.1.3-green)](https://jitpack.io/#cerus/simple-nbt/v1.1.3) [![GitHub Workflow Status](https://img.shields.io/github/workflow/status/cerus/simple-nbt/Maven)](https://github.com/cerus/simple-nbt/actions?query=workflow%3AMaven)

# simple-nbt
Very simple implementation of the [NBT (Named Binary Tag)](https://minecraft.gamepedia.com/NBT_format) format.

## Features
- Reading (un)compressed nbt
- Writing (un)compressed nbt
- Custom tag implementations
- NBT -> stringified NBT (`Tag#stringify()`)

## Installation
You can add simple-nbt to your project with Maven using Jitpack.io:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.cerus</groupId>
        <artifactId>simple-nbt</artifactId>
        <version>v1.1.3</version>
    </dependency>
</dependencies>
```

## Usage
Take a look at the [examples](src/main/java/examples) package.