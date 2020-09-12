[![Build Status](https://travis-ci.com/plokhotnyuk/calculator.png)](http://travis-ci.com/plokhotnyuk/calculator)
[![Coverage Status](https://coveralls.io/repos/github/plokhotnyuk/calculator/badge.svg?branch=master)](https://coveralls.io/github/plokhotnyuk/calculator?branch=master)

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

Inspired by the end-to-end approach from the amazing book:
http://www.growing-object-oriented-software.com/

Initially developed in Java than converted to Scala:
https://github.com/plokhotnyuk/calculator/tree/fee1b741aa74d659b8e30ad66d26d9ca6a2f6bc5

Features are described as executable specification:
https://github.com/plokhotnyuk/calculator/blob/master/src/test/scala/com/github/plokhotnyuk/calculator/CalculatorSpec.scala

## Building & running application

Instructions for building are simple:
```sh
sbt clean assembly
```
BEWARE! The end-to-end test over running Swing app will be started and possible you will be shocked by moving mouse 
pointer that will press the app buttons on your desktop. And possible you will be double-shocked to see that the same 
tests complete during Travis CI build or (in case of test failures) to read cute explanation instead of stack traces.

Run calculator by following command:
```sh
java -jar target/scala-2.13/calculator.jar
```

To build the test coverage report use:
```sh
sbt clean coverage test coverageReport
```

Have a fun to read & lean!