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

// Complete the jimOrders function below.
function jimOrders(orders) {
    var servings = [];
    var returnArr = [];
    for (let i = 0; i < orders.length; i++) {
        servings[i] = {custNumber: i + 1, serveTime: orders[i][0] + orders[i][1]};
    }
    servings.sort((a,b) => {return a.serveTime - b.serveTime});
    for (let i = 0; i < servings.length; i++) {
        returnArr.push(servings[i].custNumber);
    }
    return returnArr;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine(), 10);

    let orders = Array(n);

    for (let i = 0; i < n; i++) {
        orders[i] = readLine().split(' ').map(ordersTemp => parseInt(ordersTemp, 10));
    }

    let result = jimOrders(orders);

    ws.write(result.join(" ") + "\n");

    ws.end();
}
