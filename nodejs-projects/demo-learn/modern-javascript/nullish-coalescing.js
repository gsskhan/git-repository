let x = null;
y = x ?? 100
console.log("y = "+ y);

console.log("#####################################");
x = undefined;
y = x ?? 100
console.log("y = "+ y);

console.log("#####################################");
x = 10000;
y = x ?? 100
console.log("y = "+ y);