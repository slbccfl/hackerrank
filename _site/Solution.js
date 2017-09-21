process.stdin.resume();
process.stdin.setEncoding('ascii');
var input = "";
process.stdin.on('data', function (data) {
    input += data;
});
process.stdin.on('end', function () {
    numbers = input.split("\n");
    sum = parseInt(numbers[0]) + parseInt(numbers[1])
    prod = parseInt(numbers[0]) * parseInt(numbers[1])
    process.stdout.write(sum+"\n"+prod+"\n");
});

function main() {
    var n = parseInt(readLine());
    for (l = 0; l < n; l++) { 
        line = "";
        for (i = 0; i < n; i++) {
            if (i<n-i-1) {line += '#'} else {line += " "};
            console.log(line);
        }
        console.log(line);
    }

}

   this.isPresent = function(root, val) {
        if (val < root.data) {
            this.isPresent(root.right, val);
        }
        else 
        }
            this.isPresent(root.left, val);
        }
	};