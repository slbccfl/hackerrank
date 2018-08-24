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
    inputString = inputString.replace(/\s*$/, '')
        .split('\n')
        .map(str => str.replace(/\s*$/, ''));

    main();
});

function readLine() {
    return inputString[currentLine++];
}

// Complete the icecreamParlor function below.
function icecreamParlor(m, arr) {
    // console.log("m=" + m);
    var i, j;
    console.log("arr: " + arr);
    debugger;
    for (i = 1; i < arr.length; i++) {
        j = arr.indexOf(m - arr[i-1],i);
        // console.log("m - arr[i-1]=" + (m - arr[i]) + ", j=" + j + ", arr[j]=" + arr[j]);
        if (j > 0) break;
    }
    let returnValue = [Math.min(i, j+1), Math.max(i, j+1)];
    console.log(returnValue);
    return returnValue;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const t = parseInt(readLine(), 10);

    for (let tItr = 0; tItr < t; tItr++) {
        const m = parseInt(readLine(), 10);

        const n = parseInt(readLine(), 10);

        const arr = readLine().split(' ').map(arrTemp => parseInt(arrTemp, 10));

        let result = icecreamParlor(m, arr);

        ws.write(result.join(" ") + "\n");
    }

    ws.end();
}
