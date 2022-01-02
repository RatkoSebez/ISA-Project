import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoatInfoPageComponent } from './boat-info-page/boat-info-page.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './register/register.component';
import { WeekendCottageInfoPageComponent } from './weekend-cottage-info-page/weekend-cottage-info-page.component';

const routes: Routes = [
  {path: "register", component: RegisterComponent},
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
