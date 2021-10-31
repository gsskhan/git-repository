console.log("#################################");

interface IPerson {
    firstName: string,
    lastName: string,
    sayHi: () => string
}

let customer:IPerson = {
    firstName: "Tom",
    lastName: "Hanks",
    sayHi: () => {return "Hi There."} 
}

console.log("Customer Object ") 
console.log(customer.firstName) 
console.log(customer.lastName) 
console.log(customer.sayHi())  

let employee:IPerson = { 
   firstName:"Jim",
   lastName:"Blakes", 
   sayHi: ():string =>{return "Hello!!!"} 
} 
  
console.log("Employee  Object ") 
console.log(employee.firstName);
console.log(employee.lastName);
console.log(employee.sayHi());

//Example: Multiple Interface Inheritance
interface IParent1 { 
    v1:number 
} 
 
interface IParent2 { 
    v2:number 
} 
 
interface Child extends IParent1, IParent2 { } 
let Iobj:Child = { v1:12, v2:23} 
console.log("value 1: "+this.v1+" value 2: "+this.v2)
