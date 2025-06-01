import "reflect-metadata";
import { container, TYPES } from "./config.js";

// Resolve the dependencies
// Notice we donot explicitly declare a GameReader instance anymore
const nssConsole = container.get(TYPES.NSSConsole);

// Use the dependencies
nssConsole.play();
nssConsole.playAnotherTitle("Super Mario Bros");