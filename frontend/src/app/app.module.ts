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
import { CardComponent } from './card/card.component';
import { WeekendCottageInfoComponent } from './weekend-cottage-info/weekend-cottage-info.component';
import { FooterComponent } from './footer/footer.component';
import { WeekendCottageInfoCardComponent } from './weekend-cottage-info-card/weekend-cottage-info-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MenuComponent,
    PageNotFoundComponent,
    CardComponent,
    WeekendCottageInfoComponent,
    FooterComponent,
    WeekendCottageInfoCardComponent
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
