'use strict';

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



function main() {
    const gNodesEdges = readLine().split(' ');
    const gNodes = parseInt(gNodesEdges[0], 10);
    const gEdges = parseInt(gNodesEdges[1], 10);

    let gFrom = [];
    let gTo = [];
    let gWeight = [];

    for (var i = 0; i < gEdges; i++) {
        const gFromToWeight = readLine().split(' ');

        gFrom.push(parseInt(gFromToWeight[0], 10));
        gTo.push(parseInt(gFromToWeight[1], 10));
        gWeight.push(parseInt(gFromToWeight[2], 10));
    }

    // Write your code here.
    function edge(toNode,fromNode,weight) {
        this.lowNode = Math.min(toNode, fromNode);
        this.highNode = Math.max(toNode, fromNode);
        this.weight = weight;
    }

    let edges = {};
    let visitedNodes = []
    for (let e = 0; e < gWeight.length; e++) {
        edges.push = edge(gTo[e], gFrom[e], gWeight[e]);
        edges.sort((a,b) => {a.weight - b.weight});
        for (let i = 0; i < edges.length; i++) {
            edge = edges.shift();
            
        }


    }
    
}
