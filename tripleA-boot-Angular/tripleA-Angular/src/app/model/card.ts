export class Card {
    constructor(
        public id?: number,
        public life?: number,
        public atk?: number,
        public def?: number,
        public position?: Boolean,
        public protection?: Boolean
    ) {
        this.position = false;
        this.protection = false;
    }
}