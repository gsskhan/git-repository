import {decorate, injectable, inject, Container} from 'inversify';
import GameReader from './game-reader.js';
import NSSConsole from './nssconsole.js';

// Declare our dependecies types identifiers
export const TYPES = {
    GameReader: 'GameReader',
    NSSConsole: 'NSSConsole',
}

// Declare injectibles
decorate(injectable(), GameReader);
decorate(injectable(), NSSConsole);

// Declare the GameReader class as the first dependency of NSSConsole
decorate(inject(TYPES.GameReader), NSSConsole, 0);

// Declare bindings
const container = new Container();
container.bind(TYPES.GameReader).to(GameReader);
container.bind(TYPES.NSSConsole).to(NSSConsole);

export { container };