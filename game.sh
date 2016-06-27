#!/bin/bash

if [[ $1 == 'make' ]]; then
    javac -d bin -classpath :src:/usr/share/java/mysql-connector-java-5.1.38-bin.jar: src/BluebellAdventures/$2.java
elif [[ $1 == 'run' ]]; then
    java -classpath :bin:/usr/share/java/mysql-connector-java-5.1.38-bin.jar: BluebellAdventures/$2
fi

