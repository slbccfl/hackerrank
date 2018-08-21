'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', inputStdin => {
    inputString += inputStdin;
});

process.stdin.on('end', _ => {
    inputString = inputString.trim().split('\n').map(str => str.trim());

    main();
});

function readLine() {
    return inputString[currentLine++];
}

/*
 * Complete the timeConversion function below.
 */
function timeConversion(s) {
    /*
     * Write your code here.
     */
    let timeArr = s.split(":")
    if (timeArr[2].slice(2,4) == "PM") {
      if (timeArr[0] != 12) timeArr[0] = parseInt(timeArr[0]) + 12;
    } else {
      if (timeArr[0] == 12) timeArr[0] = "00";
    }
    return timeArr[0] + ":" + timeArr[1] + ":" + timeArr[2].slice(0,2);

}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const s = readLine();

    let result = timeConversion(s);

    ws.write(result + "\n");

    ws.end();
}
