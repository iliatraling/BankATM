import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { BankAcc } from 'src/models/BankAcc';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'bank';
  str = ' ';
  bankAcc: BankAcc = {
    ID: 0,
    NAME: 'null',
    LAST_NAME: 'null',
    START_BILL: '00-00-00',
    START_T_BILL: '00:00:00',
    BALANCE: 0,
  }
  constructor(private location: Location) {}
  onBack(): void {
    this.location.back();
  }
  
}
