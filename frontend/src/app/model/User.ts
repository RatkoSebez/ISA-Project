import { PotentialCompliant } from "./PotentialCompliant";

export class User{
    constructor(
        public id: number,
        public email: string,
        public firstName: string,
        public lastName: string,
        public address: string,
        public city: string,
        public country: string,
        public phoneNumber: number,
        public role: string,
        public potentialComplaints: Array<PotentialCompliant>,
        public loyaltyCategory: string,
        public loyaltyPoints: number,
        public loyaltyBenefits: string
    ){}
}