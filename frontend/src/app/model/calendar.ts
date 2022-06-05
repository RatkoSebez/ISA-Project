import { StickyDirection } from "@angular/cdk/table";

export class calendar{
    constructor(
        public Id: number,
        public Subject: string,
        public StartDate: Date,
        public EndDate: Date
    ){}
}