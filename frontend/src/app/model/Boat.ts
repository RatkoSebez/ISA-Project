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
        public pricePerDay: number,
        public additionalServices: string,
        public reservations: Array<BoatReservation>,
        public type: string,
        public length: number,
        public engineNumber: number,
        public enginePower: string,
        public maxSpeed: number,
        public rules: string,
        public navigation: string,
        public fishingEqu: string,
        public cancellationConditions: string,
        public boatOwnerId: number
    ){}
}