#!/bin/bash
if [ $1 = "" ]
  then $1 = "00"
fi
export OUTPUT_PATH=output$1.txt
node Solution.js < input$1.txt > output$1.txt
