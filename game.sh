#!/bin/bash

if [[ $1 == 'make' ]]; then
    javac -d bin -classpath src src/BluebellAdventures/$2.java
elif [[ $1 == 'run' ]]; then
    java -classpath bin BluebellAdventures/$2
fi

