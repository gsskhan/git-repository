console.log("#################################");

var name$:string = "John"; 
var score1:number = 50;
var score2:number = 42.50
var sum = score1 + score2 
console.log("name"+ name$ ) 
console.log("first score: "+score1) 
console.log("second score: "+score2) 
console.log("sum of the scores: "+sum)

console.log("----------------------------------");

let str1 = '100'
let str2:number = <number> <any> str1
console.log(typeof(str2));
