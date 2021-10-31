console.log("#################################");

function greet(name: string): string {
    return "Hello ! " + name;
}

// Optional parameters are specified with ?. It must me last argument in function.
function greet_optional(name?: string): string {
    return "Hello ! " + name;
}

function caller() {
    let msg1 = greet ("Gulam");
    console.log(msg1);

    let msg2 = greet_optional();
    console.log(msg2);
    
    let msg3 = greet_optional ("Golu polu");
    console.log(msg3);    
}

caller();

// Rest parameters are same like vargs in java. (Type should be an array)
function addNumbers(...nums:number[]) {
    let sum:number = 0; 
    
    for(let i = 0;i<nums.length;i++) { 
       sum = sum + nums[i]; 
    } 
    console.log("sum of the numbers",sum)        
 }

addNumbers(1,2,3) 
addNumbers(10,12,1,19,17)

// Default parameters. 
function calculate_discount(price:number,rate:number = 0.50) { 
    let discount = price * rate; 
    console.log("Discount Amount: ",discount); 
} 
calculate_discount(1000) 
calculate_discount(1000,0.30)

// Anonymous function with parameters
let res = function(a:number,b:number) { 
    return a*b;  
}; 
console.log( "Result -> " + res(12,2) ) 

// Lambda Functions
let foo = (x:number)=>10 + x 
console.log(foo(100))

// Optional parentheses for a single parameter
var display = x=> { 
   console.log("The function got "+x) 
} 
display(12);

// Optional braces for a single statement, Empty parentheses for no parameter
var disp =()=> { 
   console.log("Function invoked"); 
} 
disp();

// Inbuilt Function constructor (Belongs to javascript)
let inbuiltFn = new Function('a', 'b', 'c', 'return a+b+c');
console.log(inbuiltFn(2, 6, 3));