import { Component, OnInit } from '@angular/core';
import { BankAcc } from 'src/models/BankAcc';
import { ActivatedRoute } from '@angular/router';
import { BankaccService } from 'src/app/services/bankacc.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-withdraw-money',
  templateUrl: './withdraw-money.component.html',
  styleUrls: ['./withdraw-money.component.css']
})
export class WithdrawMoneyComponent implements OnInit {

  constructor(private route: ActivatedRoute, private bankaccService: BankaccService, private router: Router) { }
  isVisability: boolean = false;
  isNumber: boolean = false;
  withdraw: number = 0;
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
      if(this.bankAcc.ID <= 0) {
        this.router.navigate(['/']);
      } else {
        this.balance = this.bankAcc.BALANCE;
        this.isVisability = true;
      }
      
    });
  }
  onAll() {
    this.withdraw = this.bankAcc.BALANCE!;
  }
  onWithdraw() {
    console.log("balance" + this.bankAcc.BALANCE);
    this.isNumber = false;
    
    if(Number.isNaN(Number(this.withdraw)) || Number(this.withdraw) > this.bankAcc.BALANCE! || this.withdraw < 0) {
      this.isNumber =true;
      console.log(this.withdraw);
      console.log( "what" + this.bankAcc.BALANCE)
      console.log(this.withdraw > this.bankAcc.BALANCE);
    } else {
      this.balance = this.bankAcc.BALANCE-this.withdraw;
      this.bankAcc.BALANCE = -this.withdraw;
      this.bankaccService.putBankAcc(this.bankAcc).subscribe(
        item => {
        console.log("post sucsessful");
        this.withdraw = 0;
        
      });
      setTimeout(()=> {this.ngOnInit();},3000);
      
    }
  }

}
