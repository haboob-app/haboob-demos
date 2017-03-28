# Haboob Java Demo
Example of how to use haboob with Java

## Prerequisites

You will need the following things properly installed on your computer.

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi)

## Installation

* `git clone git@github.com:haboob-app/haboob-demos.git`
* `cd haboob-demos/java`

## Test

* `mvn package assembly:single`
* To run on development `java -cp target/haboob-java-example-1.0-jar-with-dependencies.jar co.haboob.app.App`
* To run on production `java -cp target/haboob-java-example-1.0-jar-with-dependencies.jar co.haboob.app.App production`