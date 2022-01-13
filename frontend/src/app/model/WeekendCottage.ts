import { CottageReservation } from "./CottageReservation";

export class WeekendCottage{
    constructor(
        public id: number,
        public name: string,
        public address: string,
        public description: string,
        public rating: number,
        public rulesOfConduct: string,
        public image: string,
        public priceList: string,
        public reservations: Array<CottageReservation>,
        public additionalServices: string
    ){}
}