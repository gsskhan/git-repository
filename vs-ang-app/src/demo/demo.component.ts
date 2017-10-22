import { Component  } from '@angular/core';

@Component({
    selector: 'app-root',
    //template: '<h1>hello world - {{title}} </h1>',
    templateUrl: './demo.component.html'

})
export class DemoComponent{
    public title : string = 'from Gulam';

    public showSomeMessage() {
        alert('Hi buddy! you clicked a key on keyboard!!!');
    }

    public showSomeMessageWithKeyPressedValue(keyPressEvent) {
        this.title = keyPressEvent.target.value;
    }
}