# Compiling this software
In a terminal use the `javac` command to compile this software. Note that the path to the javafx sdk and the squlite jdbc needs to be substituted into this path.

Ensure this command is run from the root directory of the project

```
javac ./main/java/*.java --module-path /{PATHTOJAVAFX}/javafx-sdk-17.0.0.1/lib --add-modules javafx.controls,javafx.fxml -classpath /{PATHTOSQLITEJDBC}/sqlite-jdbc-3.40.0.0.jar
```

# Running this software
In a terminal use the `java` command to execute. Note, that the path to the Javafx sdk and sqlite jdbc needs to be substituted into the path.

```
java --module-path /{PATHTOJAVAFX}/javafx-sdk-17.0.0.1/lib --add-modules javafx.controls,javafx.fxml -cp /{PATHTOSQLITEJDBC}/sqlite-jdbc-3.40.0.0.jar:src main.java.Main
```

# Versions of dependencies
* JDK - jdk-17.0.5
* JavaFX - javafx-sdk-17.0.0.1
* JDBC - sqlite-jdbc-3.40.0.0

## IDE
Visual Studio Code - MacOS

## Specfic to my machine
javac ./main/java/*.java --module-path /Users/jacobpyke/Downloads/javafx-sdk-17.0.0.1/lib --add-modules javafx.controls,javafx.fxml -classpath /Users/jacobpyke/Downloads/sqlite-jdbc-3.40.0.0.jar

javac ./main/java/*.java --module-path /Users/jacobpyke/Downloads/javafx-sdk-17.0.0.1/lib --add-modules javafx.controls,javafx.fxml -classpath /Users/jacobpyke/Downloads/sqlite-jdbc-3.40.0.0.jar