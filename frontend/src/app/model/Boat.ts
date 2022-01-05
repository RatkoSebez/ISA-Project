import { BoatReservation } from "./BoatReservation";

export class Boat{
    constructor(
        public id: number,
        public name: string,
        public address: string,
        public description: string,
        public rating: number,
        public image: string,
        public capacity: number,
        public priceList: string,
        public additionalServices: string,
        public reservations: Array<BoatReservation>
    ){}
}