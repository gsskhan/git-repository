console.log("#################################");

class Car {

    //field
    engine:string;

    // constructor
    constructor(eng:string) {
        this.engine = eng;
    }

    //function
    disp():void {
        console.log("Engine is  :   "+this.engine)
    }
}

//create an object 
let carObj = new Car("Engine 1");
//access the field 
console.log("Reading attribute value Engine as :  "+carObj.engine)  
//access the function
carObj.disp()

// Class Inheritance - Every class can at the most extend from one parent class. TypeScript doesn’t support multiple inheritance.
class Shape { 
    Area:number 
    
    constructor(a:number) { 
       this.Area = a 
    } 
} 
 
class Circle extends Shape { 
    disp():void { 
       console.log("Area of the circle:  "+this.Area) 
    } 
}
   
let circleObj = new Circle(223); 
circleObj.disp()

//  Class inheritance and Method Overriding
class PrinterClass { 
    private str2:string = "test" 

    doPrint():void {
       console.log("doPrint() from Parent called…") 
    } 
} 
 
class StringPrinter extends PrinterClass { 
    doPrint():void {         
       super.doPrint() 
       console.log("doPrint() is printing a string…")
    } 
} 
 
let stringPrinterObj = new StringPrinter() 
stringPrinterObj.doPrint()

// Classes can also implement interfaces.
interface ILoan { 
    interest:number 
 } 
 
class AgriLoan implements ILoan { 
    interest:number 
    rebate:number 
    
    constructor(interest:number,rebate:number) { 
       this.interest = interest 
       this.rebate = rebate 
    } 
} 
 
let loanObj = new AgriLoan(10,1) 
console.log("Interest is : "+loanObj.interest+" Rebate is : "+loanObj.rebate )