#!/bin/bash

args="compile package"

while getopts "c" opt; do
  case $opt in
    c) echo "Adding 'clean' target to maven"
       args="clean "${args}
       ;;
  esac
done

mvn $args
