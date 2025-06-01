export default class GameReader {

    constructor(input = "TurboCars Racer") {
        this.input = input;
    }

    readDisc() {
        console.log("Now playing: ", this.input);
    }

    changeDisc(input) {
        this.input = input;
        this.readDisc();
    }
    
}