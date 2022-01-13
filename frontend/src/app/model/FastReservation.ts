export class FastReservation{
    constructor(
        public id: number,
        public entity: string,
        public entityId: number,
        public startDate: Date,
        public endDate: Date,
        public clientEmail: string,
        public oldPrice: number,
        public newPrice: number
    ){}
}