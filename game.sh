#!/bin/bash

if [[ $1 == 'make' ]]; then
    javac -d bin -classpath src src/BluebellAdventures/Main.java
elif [[ $1 == 'run' ]]; then
    java -classpath bin BluebellAdventures/Main
fi

