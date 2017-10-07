[![Build Status](https://secure.travis-ci.org/plokhotnyuk/calculator.png)](http://travis-ci.org/plokhotnyuk/calculator)

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

## Building & running application

Use following command-line instructions for building:
```sh
sbt clean assembly
```
BEWARE! End-to-end test over running Swing app will be started and possible you will be shocked by moving mouse pointer 
that will press the app buttons in your desktop. And possible you will be double-shocked to see that the same tests are
completes during Travis CI build, or in case of test failures to see cute explanation instead of stack traces.

Run calculator by following command:
```sh
java -jar calculator.jar
```
Have a fun to read & lean!