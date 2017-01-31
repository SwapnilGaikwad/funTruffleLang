#!/bin/bash

usage() {

    echo "./build.sh [-c]"
    echo "    -c : Clean the old class files"
}
args="compile package"

while getopts "cgh" opt; do
  case $opt in
    c) echo "Adding 'clean' target to maven"
       args="clean "${args}
       ;;
    *) usage
       exit 1
  esac
done

mvn $args
