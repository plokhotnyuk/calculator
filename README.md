![Build Status](https://secure.travis-ci.org/plokhotnyuk/calculator.png)](http://travis-ci.org/plokhotnyuk/calculator)

```sh
  ___                            ___
 ||  |   ___         ___        ||  |
 || _|__/  _\_______/  _\_______|| _|
 ||(___(  (________(  (_________||((_)
 ||  |  \___/       \___/       ||  |
 ||  |         ___              ||  |
 || _|________/  _\_____________|| _|
 ||(_________(  (_______________||((_)
 ||  |        \___/             ||  |
 ||  |                          ||  |
 ||  |       CALCULATOR         ||  |
 ||  |                          ||  |
```

Inspired by end-to-end approach from the amazing book:
http://www.growing-object-oriented-software.com/

Initially developed in Java than converted to Scala:
https://github.com/plokhotnyuk/calculator/tree/fee1b741aa74d659b8e30ad66d26d9ca6a2f6bc5

Features are described as executable specification:
https://github.com/plokhotnyuk/calculator/blob/master/src/test/scala/com/github/plokhotnyuk/calculator/CalculatorSpec.scala

## Software installed required

- JDK: 1.6.0_x or newer
- Maven: 3.0.x (or sbt: 0.11.3)

## Building & running application

Use following command-line instructions for building:
```sh
mvn -B clean install >out.txt (or sbt clean test one-jar >out.txt)
```
Run calculator by following command:
```sh
java -jar scala-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
```