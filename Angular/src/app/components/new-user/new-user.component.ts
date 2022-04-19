import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BankaccService } from 'src/app/services/bankacc.service';
import { BankAcc } from 'src/models/BankAcc';


@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {
  isVisability: boolean = false;
  bankAcc: BankAcc = {
    ID: 0,
    NAME: "",
    LAST_NAME: "",
    START_BILL: '2022-04-15',
    START_T_BILL: '21:59:17',
    BALANCE: 0,
  }
  namee: string = "hello";
  constructor( private route: ActivatedRoute, private bankaccService: BankaccService, private router: Router) { }
  setID( ) {
    if(this.bankAcc.ID<0) {
      ;
    }
  }

  onCreate() {
    this.bankaccService.postBankAcc(this.bankAcc).subscribe(item => {
      this.isVisability = false;
      this.bankAcc = item;
      console.log("id is " + this.bankAcc.ID);
      if(this.bankAcc.ID <= 0) {
        console.log("this accaunt is alredy exist");
          this.isVisability = true;
      } else {
        console.log("create account succsesfull");
          this.router.navigate(['/']);
          this.isVisability = false;
      }
    });
  }


  
  
  ngOnInit(): void {
  }
}

