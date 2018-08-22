#!/bin/bash
if [ -z "$1" ]
then
  fileNum="00"
else
  fileNum=$1
fi
export OUTPUT_PATH=output$fileNum.txt
node Solution.js < input$fileNum.txt
