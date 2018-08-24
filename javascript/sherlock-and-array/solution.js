'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', function (inputStdin) {
    inputString += inputStdin;
});

process.stdin.on('end', function () {
    inputString = inputString.split('\n');

    main();
});

function readLine() {
    return inputString[currentLine++];
}

// Complete the balancedSums function below.
function balancedSums(arr) {
    // console.log(arr);
    let leftSum = 0;
    let rightSum = 0;
    let l = 0;
    let r = arr.length - 1;
    while (l < r) {
        // console.log("leftSum = " + leftSum);
        // console.log("rightSum = " + rightSum);
        // console.log("l = " + l);
        // console.log("r = " + r);
        if (leftSum > rightSum) {
            rightSum += arr[r];
            r--;
        } else if (leftSum < rightSum) {
            leftSum += arr[l];
            l++;
        } else if (arr[l] > arr[r]) {
            rightSum += arr[r];
            r--;
        } else if (arr[l] < arr[r]) {
            leftSum += arr[l];
            l++;
        } else {
            rightSum += arr[r];
            r--;
            leftSum += arr[l];
            l++;
        }
        
    }
    // console.log("Final leftSum = " + leftSum);
    // console.log("Final rightSum = " + rightSum);
    // console.log("Final l = " + l);
    // console.log("Final r = " + r);
    if ((l == r) && (leftSum == rightSum)) {
        return "YES";
    } else {
        return "NO";
    }



}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const T = parseInt(readLine().trim(), 10);

    for (let TItr = 0; TItr < T; TItr++) {
        const n = parseInt(readLine().trim(), 10);

        const arr = readLine().replace(/\s+$/g, '').split(' ').map(arrTemp => parseInt(arrTemp, 10));

        const result = balancedSums(arr);

        ws.write(result + '\n');
    }

    ws.end();
}
