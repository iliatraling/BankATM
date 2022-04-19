import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { BankAcc } from 'src/models/BankAcc';
import { ActivatedRoute } from '@angular/router';
import { BankaccService } from 'src/app/services/bankacc.service';
import { Router } from '@angular/router';




@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor(private route: ActivatedRoute, private bankaccService: BankaccService, private router: Router) { }

  ngOnInit(): void {
  }

  isVisability: boolean = false;
  bankAcc: BankAcc = {
    ID: 0,//iss
    NAME: 'null',
    LAST_NAME: 'null',
    START_BILL: '2022-04-15',
    START_T_BILL: '21:59:17',
    BALANCE: 0,
  }// можно добавить флаг, чтобы не отображался юзер если не передали 
  onGet() {
    this.isVisability = false;
    //this.router.navigate(['/user/' + this.bankAcc.ID]);
    this.bankaccService.getBankAcc(this.bankAcc.ID).subscribe(item => {
      this.bankAcc = item;
     
      if(this.bankAcc.ID <= 0) {
        this.isVisability = true;
        //setTimeout(()=> {this.isVisability = true;},3000);
        console.log("ID is not exist");
        
      } else {
        this.router.navigate(['/user/'+ this.bankAcc.ID]);
      }  
    });
  }

  keyboard(key: number) :void {
    switch (key) {
        case 1:
        this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 2:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 3:
            this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 4:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 5:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 6:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 7:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 8:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 9:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + key);
        break;
        case 10:
        let str = String(this.bankAcc.ID);
        if(str.length == 1) {
          this.bankAcc.ID = 0;
          break;
        }
        this.bankAcc.ID = Number.parseInt(str.substring(0,str.length-1));
        break;
        case 11:
          this.bankAcc.ID = Number.parseInt(String(this.bankAcc.ID) + 0);
        break;
        case 12:
          this.bankAcc.ID = 0;
        break;
    }

  }
  

}
