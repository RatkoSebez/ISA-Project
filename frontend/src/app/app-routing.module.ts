import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureInfoPageComponent } from './adventure-info-page/adventure-info-page.component';
import { AdventureReservationsComponent } from './adventure-reservations/adventure-reservations.component';
import { AdventuresPageComponent } from './adventures-page/adventures-page.component';
import { BoatInfoPageComponent } from './boat-info-page/boat-info-page.component';
import { BoatReservationsComponent } from './boat-reservations/boat-reservations.component';
import { BoatsPageComponent } from './boats-page/boats-page.component';
import { BookedReservationsComponent } from './booked-reservations/booked-reservations.component';
import { CottageReservationsComponent } from './cottage-reservations/cottage-reservations.component';
import { FastReservationAdventureComponent } from './fast-reservation-adventure/fast-reservation-adventure.component';
import { FastReservationBoatComponent } from './fast-reservation-boat/fast-reservation-boat.component';
import { FastReservationCottageComponent } from './fast-reservation-cottage/fast-reservation-cottage.component';
import { HomeComponent } from './home/home.component';
import { MakeComplaintComponent } from './make-complaint/make-complaint.component';
import { AdventureReservation } from './model/AdventureReservation';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './register/register.component';
import { UserInfoPageComponent } from './user-info-page/user-info-page.component';
import { WeekendCottageInfoPageComponent } from './weekend-cottage-info-page/weekend-cottage-info-page.component';
import { WeekendCottagesPageComponent } from './weekend-cottages-page/weekend-cottages-page.component';

const routes: Routes = [
  {path: "register", component: RegisterComponent},
  {path: "user", component: UserInfoPageComponent},
  {path: "adventures", component: AdventuresPageComponent},
  {path: "fastReservationBoat", component: FastReservationBoatComponent},
  {path: "fastReservationCottage", component: FastReservationCottageComponent},
  {path: "fastReservationAdventure", component: FastReservationAdventureComponent},
  {path: "adventure", component: AdventureInfoPageComponent},
  {path: "adventureReservations", component: AdventureReservationsComponent},
  {path: "makeCompliant", component: MakeComplaintComponent},
  {path: "bookedReservations", component: BookedReservationsComponent},
  {path: "boatReservations", component: BoatReservationsComponent},
  {path: "cottageReservations", component: CottageReservationsComponent},
  {path: "boats", component: BoatsPageComponent},
  {path: "cottages", component: WeekendCottagesPageComponent},
  {path: "home", component: HomeComponent},
  {path: "weekendCottage", component: WeekendCottageInfoPageComponent},
  {path: "boat", component: BoatInfoPageComponent},
  {path: "", redirectTo: '/home', pathMatch: 'full'},
  {path: "**", component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
