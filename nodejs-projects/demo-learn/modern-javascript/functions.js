let age = 17;

let welcome = (age < 18) ?
  function() { console.log("Hello!"); } :
  function() { console.log("Greetings!"); };

welcome();

home("gugu");

function home(msg) {
    console.log(`At home ---->  ${msg}`);
}

console.log("#####################################");

let say = (a, b) => {
  return a+b;
}; 

console.log( say(10,20) );

let sayAgain = () => {
  console.log("saying again !!!");
}

sayAgain();