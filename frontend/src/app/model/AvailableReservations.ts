export class AvailableReservations{
    constructor(
        public id: number,
        public entity: string,
        public entityId: number,
        public startDate: Date,
        public endDate: Date,
        public oldPrice: number,
        public newPrice: number,
        public expirationDate: Date
    ){}
}