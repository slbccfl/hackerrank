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

// Complete the cavityMap function below.
function cavityMap(grid) {
    let newGrid = grid.slice(0);
    for (let i = 1; i < grid.length - 1; i++) {
        for (let j = 1; j < grid.length - 1; j++) {
            let cellValue = grid[i].charAt(j);
            // console.log(cellValue);
            // console.log(grid[i-1].charAt(j) +
            //     grid[i+1].charAt(j) +
            //     grid[i].charAt(j-1) +
            //     grid[i].charAt(j+1));

            if (cellValue > grid[i-1].charAt(j) &&
                cellValue > grid[i+1].charAt(j) &&
                cellValue > grid[i].charAt(j-1) &&
                cellValue > grid[i].charAt(j+1))
                newGrid[i] = newGrid[i].substr(0,j) + "X" + newGrid[i].substr(j+1);
        }
    }
    return newGrid;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine(), 10);

    let grid = [];

    for (let i = 0; i < n; i++) {
        const gridItem = readLine();
        grid.push(gridItem);
    }

    let result = cavityMap(grid);

    ws.write(result.join("\n") + "\n");

    ws.end();
}
