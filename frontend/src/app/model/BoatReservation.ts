export class BoatReservation{
    constructor(
        public id: number,
        public startDate: Date,
        public endDate: Date,
        public clientEmail: string,
        public foreign_key: number
    ){}
}