import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './components/main-page/main-page.component';
import { IfUserComponent } from './components/if-user/if-user.component';
import { AboutComponent } from './components/about/about.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { NewUserComponent } from './components/new-user/new-user.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { InfoAccComponent } from './components/info-acc/info-acc.component';
import { WithdrawMoneyComponent } from './components/withdraw-money/withdraw-money.component';
import { PutMoneyComponent } from './components/put-money/put-money.component';
import { CloseAccComponent } from './components/close-acc/close-acc.component';

const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'about', component: AboutComponent},
  {path: 'newuser', component: NewUserComponent},
  {path: 'user/:id', component: IfUserComponent},
  {path: 'signin', component: SignInComponent},
  {path: 'info/:id', component: InfoAccComponent},
  {path: 'withdraw/:id', component: WithdrawMoneyComponent},
  {path: 'put/:id', component: PutMoneyComponent},
  {path: 'close/:id', component: CloseAccComponent},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
