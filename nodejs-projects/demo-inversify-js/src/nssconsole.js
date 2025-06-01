export default class NSSConsole {

    constructor(gameReader){
        this.gameReader = gameReader;
    }

    play() {
        this.gameReader.readDisc();
    }

    playAnotherTitle(input) {
        this.gameReader.changeDisc(input);
    }
    
}