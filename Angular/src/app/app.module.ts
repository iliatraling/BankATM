import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MainPageComponent } from './components/main-page/main-page.component';
import { IfUserComponent } from './components/if-user/if-user.component';
import { AboutComponent } from './components/about/about.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { FormsModule } from '@angular/forms';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { CloseAccComponent } from './components/close-acc/close-acc.component';
import { InfoAccComponent } from './components/info-acc/info-acc.component';
import { PutMoneyComponent } from './components/put-money/put-money.component';
import { WithdrawMoneyComponent } from './components/withdraw-money/withdraw-money.component';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    IfUserComponent,
    AboutComponent,
    PageNotFoundComponent,
    NewUserComponent,
    SignInComponent,
    CloseAccComponent,
    InfoAccComponent,
    PutMoneyComponent,
    WithdrawMoneyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
