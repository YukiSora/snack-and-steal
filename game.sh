#!/bin/bash

if [[ $1 == 'make' ]]; then
    javac -d bin -classpath :src:resource/mysql.jar: src/BluebellAdventures/$2.java
elif [[ $1 == 'run' ]]; then
    java -classpath :bin:resource/mysql.jar: BluebellAdventures/$2
fi

