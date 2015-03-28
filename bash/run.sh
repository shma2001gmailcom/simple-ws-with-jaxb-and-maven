#!/bin/sh

#set M2_HOME here
M2_HOME='/opt/apache-maven-3.2.2/'
export M2_HOME
M2=${M2_HOME}/bin
export M2
PATH=${PATH}:${M2}
export PATH

cd ../
mvn clean package
cd target
java -jar converter-ws-1.0-SNAPSHOT.jar
