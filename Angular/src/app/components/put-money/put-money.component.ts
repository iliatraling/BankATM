import { Component, OnInit } from '@angular/core';
import { BankAcc } from 'src/models/BankAcc';
import { ActivatedRoute } from '@angular/router';
import { BankaccService } from 'src/app/services/bankacc.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-put-money',
  templateUrl: './put-money.component.html',
  styleUrls: ['./put-money.component.css']
})
export class PutMoneyComponent implements OnInit {

  constructor(private route: ActivatedRoute, private bankaccService: BankaccService, private router: Router) { }
  isVisability: boolean = false;
  isSucsessfull: boolean = false;
  put: number = 0;
  balance: number = 0;
  bankAcc: BankAcc = {
    ID: 0,
    NAME: 'null',
    LAST_NAME: 'null',
    START_BILL: '00-00-00',
    START_T_BILL: '00:00:00',
    BALANCE: 0,
  }// можно добавить флаг, чтобы не отображался юзер если не передали 

  ngOnInit(): void {
    this.bankAcc.ID = this.route.snapshot.params['id'];
    this.bankaccService.getBankAcc(this.bankAcc.ID).subscribe(item => {
      this.bankAcc = item;
      this.balance = this.bankAcc.BALANCE;
      if(this.bankAcc.ID <= 0) {
        setTimeout(()=> {this.router.navigate(['/']);},3000);
      } 
      
    });
  }
  onPut() {
    this.isSucsessfull = false;
    this.isVisability = false;
    if(Number.isNaN(Number(this.put)) || Number(this.put)<0) {
      this.isVisability =true;
    } else {
      this.balance = this.bankAcc.BALANCE - -Number(this.put) ;
      this.bankAcc.BALANCE = this.put;
      this.bankaccService.putBankAcc(this.bankAcc).subscribe(
        item => {
        console.log("post sucsessful");
        this.isSucsessfull = true;
      });
    }
    setTimeout(()=> {this.ngOnInit();},3000);
  }

}
