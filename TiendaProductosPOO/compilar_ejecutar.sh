#!/bin/bash
mkdir -p bin
javac -encoding UTF-8 -d bin src/modelo/*.java src/negocio/*.java src/interfaz/*.java
java -cp bin interfaz.Main
