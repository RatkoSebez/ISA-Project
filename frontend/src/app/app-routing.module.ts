import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatInfoPageComponent } from './boat-info-page/boat-info-page.component';
import { BoatsPageComponent } from './boats-page/boats-page.component';
import { BookedReservationsComponent } from './booked-reservations/booked-reservations.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './register/register.component';
import { UserInfoPageComponent } from './user-info-page/user-info-page.component';
import { WeekendCottageInfoPageComponent } from './weekend-cottage-info-page/weekend-cottage-info-page.component';
import { WeekendCottagesPageComponent } from './weekend-cottages-page/weekend-cottages-page.component';

const routes: Routes = [
  {path: "register", component: RegisterComponent},
  {path: "user", component: UserInfoPageComponent},
  {path: "bookedReservations", component: BookedReservationsComponent},
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
