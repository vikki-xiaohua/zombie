## Environment

```
Java 11
Maven
```

## Test

```
mvn clean test
```

## Run

```
mvn compile exec:java -Dexec.mainClass="XXX.java" -Dexec.args="XXX.txt"

eg.
mvn compile exec:java -Dexec.mainClass=" src/main/java/Zombie.java" -Dexec.args="src/test/java/test_1.txt"
```

## Key assumptions

```
 1. Small input data set from txt file

 2. Input data formats are always legal (some basic checks are included in the codes, but far from enough)

```