class Person {
    name: string;    
    constructor(name: string) {
        this.name = name;
    }
    sayHello(): string {
        return 'Hello '+ this.name;
    }
}
var person = new Person('Gulam');
console.log(person.name);
console.log(person.sayHello());
