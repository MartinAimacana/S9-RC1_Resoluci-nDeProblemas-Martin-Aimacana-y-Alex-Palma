@echo off
echo Compilando proyecto...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\modelo\*.java src\negocio\*.java src\interfaz\*.java
if %errorlevel% neq 0 (
    echo Error de compilacion.
    pause
    exit /b
)
echo Ejecutando sistema...
java -cp bin interfaz.Main
pause
