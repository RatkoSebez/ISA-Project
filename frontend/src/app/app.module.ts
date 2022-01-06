import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserRegistrationService } from './user-registration.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CardComponent } from './weekend-cottage-card/card.component';
import { FooterComponent } from './footer/footer.component';
import { BoatCardComponent } from './boat-card/boat-card.component';
import { BoatInfoPageComponent } from './boat-info-page/boat-info-page.component';
import { WeekendCottageInfoPageComponent } from './weekend-cottage-info-page/weekend-cottage-info-page.component';
import { CottagesFilterPipe } from './pipes/cottages-filter.pipe';
import { HomeForGuestsComponent } from './home-for-guests/home-for-guests.component';
import { BoatsFilterPipe } from './pipes/boats-filter.pipe';
import { RegisterComponent } from './register/register.component';
import { UserInfoPageComponent } from './user-info-page/user-info-page.component';
import { HomeForClientComponent } from './home-for-client/home-for-client.component';
import { BoatsPageComponent } from './boats-page/boats-page.component';
import { WeekendCottagesPageComponent } from './weekend-cottages-page/weekend-cottages-page.component';
import { BookedReservationsComponent } from './booked-reservations/booked-reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MenuComponent,
    PageNotFoundComponent,
    CardComponent,
    FooterComponent,
    BoatCardComponent,
    BoatInfoPageComponent,
    WeekendCottageInfoPageComponent,
    CottagesFilterPipe,
    HomeForGuestsComponent,
    BoatsFilterPipe,
    RegisterComponent,
    UserInfoPageComponent,
    HomeForClientComponent,
    BoatsPageComponent,
    WeekendCottagesPageComponent,
    BookedReservationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserRegistrationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
