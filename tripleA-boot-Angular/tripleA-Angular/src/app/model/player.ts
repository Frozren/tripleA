import { Card } from './card';

export class Player {
    constructor(
        public id?: number,
        public idOpponent?: number,
        public name?: string,
        public typePlayer?: Boolean,
        public card1?: Card,
        public card2?: Card,
        public card3?: Card
    ) {}
}