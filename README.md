[![badge](https://img.shields.io/badge/jitpack.io-v1.1.0-green)](https://jitpack.io/#cerus/simple-nbt/v1.1.0)

# simple-nbt
Very simple implementation of the [NBT (Named Binary Tag)](https://minecraft.gamepedia.com/NBT_format) format.

## Features
- Reading (un)compressed nbt
- Writing (un)compressed nbt (Compressing  doesn't seem to work yet)

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
        <version>v1.1.0</version>
    </dependency>
</dependencies>
```

## Usage
Take a look at the [examples](src/main/java/examples) package.