export class User{
    constructor(
        public email: string,
        public firstName: string,
        public lastName: string,
        public address: string,
        public city: number,
        public country: string,
        public phoneNumber: number,
        public role: number
    ){}
}