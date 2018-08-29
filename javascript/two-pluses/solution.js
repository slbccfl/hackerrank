'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;
// SLB: making rows and columns global variables since they are of common interest the functions in the solution
var rows;
var columns;

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

// Complete the twoPluses function below.

function scoreCell(grid, r, c) {
    let maxRange;
    let cellScore;
    maxRange = Math.min(columns - c - 1, Math.min(c, Math.min(r, rows - r - 1)));
    for (let i = 1; i <= maxRange; i++) {
        if (grid[r - i][c] != 'G' ||
            grid[r + i][c] != 'G' ||
            grid[r][c - i] != 'G' ||
            grid[r][c + i] != 'G') {
            maxRange = i - 1;
            break;
        }
    }

    return maxRange;
}

function findGridHighScore(grid) {
    let highScore = -1;
    let maxRange = 0;
    let cellScore = 0;
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < columns; c++) {
            cellScore = 0;
            if (grid[r][c] == 'G') {
                if (r != 0 && c != 0 && rows - r - 1 != 0 && columns - c - 1 != 0) {
                    maxRange = scoreCell(grid, r, c);
                }
                cellScore = 1 + (maxRange * 4);
            }
            if (cellScore > highScore) {
                highScore = cellScore;
            }
        }
    }
    return highScore;
}

function tagPlus(grid, maxRange, highRow, highCol) {
    grid[highRow][highCol] = '+';
    for (let i = 1; i <= maxRange ; i++) {
        grid[highRow - i][highCol] = '+';
        grid[highRow + i][highCol] = '+';
        grid[highRow][highCol - i] = '+';
        grid[highRow][highCol + i] = '+';
    }
    return grid;
}

function twoPluses(grid) {
    for (let i = 0; i < rows; i++) grid[i] = grid[i].split('');
    let highScore = -1;
    let maxRange = 0;
    let firstCellScore = 0;
    for (let r = 0; r < rows; r++) {
        for (let c = 0; c < columns; c++) {
            firstCellScore = 0;
            if (grid[r][c] == 'G') {
                maxRange = scoreCell(grid, r, c);
                for (let tempMaxRange = maxRange; tempMaxRange >= 0; tempMaxRange--) {
                    firstCellScore = 1 + (tempMaxRange * 4);
                    let tempGrid = [];
                    for (let r2 = 0; r2 < rows; r2++) tempGrid[r2] = grid[r2].slice(0);
                    let secondCellScore = findGridHighScore(tagPlus(tempGrid, tempMaxRange, r, c));
                    let gridScore = firstCellScore * secondCellScore;
                    if (gridScore > highScore) highScore = gridScore;
                }
            }
        }
    }
    return highScore;
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const nm = readLine().split(' ');

    rows = parseInt(nm[0], 10);

    columns = parseInt(nm[1], 10);

    let grid = [];

    for (let i = 0; i < rows; i++) {
        const gridItem = readLine();
        grid.push(gridItem);
    }

    let result = twoPluses(grid);

    ws.write(result + "\n");

    ws.end();
}
