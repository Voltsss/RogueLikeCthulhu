#!/bin/bash

TOOLS_DIR=$(cd $(dirname $0); pwd)
PROJECT_ROOT=$TOOLS_DIR/../

VERSION=`cat $PROJECT_ROOT/build.sbt | grep ^version | cut -d\" -f2`

sbt clean
sbt assembly

java -jar $PROJECT_ROOT/target/scala-2.10/roguelike-${VERSION}.jar

