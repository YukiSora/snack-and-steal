#!/bin/bash

if [[ $1 == 'make' ]]; then
    javac -d bin -classpath Megumin/Actions/:Megumin/Nodes/:. Main.java
elif [[ $1 == 'run' ]]; then
    java -classpath bin/Megumin/Actions/:bin/Megumin/Nodes/:bin Main
fi

