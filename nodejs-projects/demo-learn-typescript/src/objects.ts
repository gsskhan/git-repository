console.log("#################################");

let person = {
    firstname: "Tom",
    lastname: "Hanks"
};
//access the object values 
console.log(person.firstname)
console.log(person.lastname)

// Objects as function parameters
let invokeperson = function(obj: { firstname:string, lastname :string }) { 
    console.log("first name :"+obj.firstname) 
    console.log("last name :"+obj.lastname) 
} 
invokeperson(person)