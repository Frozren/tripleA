export class Survey {
    constructor(
        public id?: number,
        public genre?: string,
        public age?: number,
        public support?: string,
        public ville?: string,
        public pays?: string,
        public note?: string,
        public ressenti?: string,
    ) {}
}