import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserRegistrationService } from './user-registration.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
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
    RegisterComponent
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
