import { Component, OnInit } from '@angular/core';
import { BankAcc } from 'src/models/BankAcc';
import { ActivatedRoute } from '@angular/router';
import { BankaccService } from 'src/app/services/bankacc.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';


@Component({
  selector: 'app-close-acc',
  templateUrl: './close-acc.component.html',
  styleUrls: ['./close-acc.component.css']
})
export class CloseAccComponent implements OnInit {

  constructor( private route: ActivatedRoute, private bankaccService: BankaccService, private router: Router) { }
  isVisability: boolean = false;
  isBalance: boolean = false;
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
        setTimeout(()=> {this.router.navigate(['/']);},3000);
      } else {
        this.isVisability = true;
        if(this.bankAcc.BALANCE<=1) {
          this.isBalance = false;
        }else{
          this.isBalance = true;
        }
        
      }
      
    });
    
  }

  onWithdraw() {
    this.router.navigate(['/withdraw/' + this.bankAcc.ID]);
  }
  onDelete() {
    this.bankaccService.deleteBankAcc(this.bankAcc.ID).subscribe(()=>{
      console.log("delete sucsessful");
    });

      setTimeout(()=> {this.router.navigate(['/']);},3000);
  }
}
