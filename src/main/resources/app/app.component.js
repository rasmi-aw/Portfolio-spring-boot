class Component {
    init();

    //
    constructor() {
        this.init();
    }
}

class AppComponent extends Component {
    #stuff;

    init() {

    }


    static {
        // get http to do
    }
}

new AppComponent()