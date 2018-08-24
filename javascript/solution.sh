#!/bin/bash
if [ -z "$1" ]
then
  fileNum="00"
else
  fileNum=$1
fi
export OUTPUT_PATH=output$fileNum.txt
if [ "$2" = "debug" ]
then
  node --inspect-brk=12345 solution.js < input$fileNum.txt
else
  node solution.js < input$fileNum.txt
fi
