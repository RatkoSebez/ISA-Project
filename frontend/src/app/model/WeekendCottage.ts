import { LongDateFormatKey } from "moment";
import { CottageReservation } from "./CottageReservation";

export class WeekendCottage{
    constructor(
        public id: number,
        public name: string,
        public address: string,
        public description: string,
        public rating: number,
        public image: string,
        public priceList: number,
        public reservations: Array<CottageReservation>,
        public rulesOfConduct: string,
        public cottageOwnerId: any,
        public additionalServices: string,
        public longitude: number,
        public latitude: number
    ){}
}