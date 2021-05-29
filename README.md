[![badge](https://img.shields.io/badge/jitpack.io-v1.1.4-green)](https://jitpack.io/#cerus/simple-nbt/v1.1.4) [![GitHub Workflow Status](https://img.shields.io/github/workflow/status/cerus/simple-nbt/Maven)](https://github.com/cerus/simple-nbt/actions?query=workflow%3AMaven)

# simple-nbt

Very simple implementation of the [NBT (Named Binary Tag)](https://minecraft.gamepedia.com/NBT_format) format.

## Features

- Reading (un)compressed nbt
- Writing (un)compressed nbt
- Custom tag implementations
- NBT -> stringified NBT (`Tag#stringify()`)

## Installation

simple-nbt is available in the Maven central repository.

```xml

<dependencies>
    <depencency>
        <groupId>dev.cerus</groupId>
        <artifactId>simple-nbt</artifactId>
        <version>1.1.8</version> <!-- Replace with latest version -->
        <scope>compile</scope>
    </depencency>
</dependencies>
```

<details>
  <summary>Prior to 1.1.8</summary>

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
    <version>v1.1.4</version>
</dependency>
</dependencies>
```

</details>

## Usage

Take a look at the [examples](src/main/java/examples)

## References

- [Minecraft Wiki - NBT Format](https://minecraft.gamepedia.com/NBT_format)
- [Minecraft NBT Docs](http://web.archive.org/web/20110723210920/http://www.minecraft.net/docs/NBT.txt)