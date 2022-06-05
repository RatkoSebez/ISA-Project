export class CottageReservation{
    constructor(
        public id: number,
        public startDate: Date,
        public endDate: Date,
        public clientEmail: string,
        public price: number,
        public foreign_key: number,
        public canceled: Boolean,
        public cottageId: number
    ){}
}