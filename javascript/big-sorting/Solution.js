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

// Complete the bigSorting function below.
function bigSorting(unsorted) {
    return unsorted.sort(function(a,b) {charCompare(a, b)});
}

function charCompare(a,b) {
    if (a.length > b.length) {
        // a is longer than b, so a bigger number
        return 1;
    } else if (a.length < b.length) {
        // converse: b is longer/bigger number than a
        return -1;
    } else {
        // strings are equal length, so compare each number until one is larger
        for (let i = 0; i < a.length; i++ ) {
            if (a.charAt(i) > b.charAt(i)) {
                return 1
            } else if (a.charAt(i) < b.charAt(i)) {
                return -1
            }
        }
        return 0;
    }
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine(), 10);

    let unsorted = [];

    for (let i = 0; i < n; i++) {
        const unsortedItem = readLine();
        unsorted.push(unsortedItem);
    }

    let result = bigSorting(unsorted);

    ws.write(result.join("\n") + "\n");

    ws.end();
}
