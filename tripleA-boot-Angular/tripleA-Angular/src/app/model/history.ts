export class History {
    constructor(
        public id?: number,
        public dmgTaken?: number,
        public dmgDealt?: number,
        public phase?: number,
        public etat?: Boolean,
        public dateEnd?: Date,
        public name?: string,
        public nbWin?: number
    ) {}
}