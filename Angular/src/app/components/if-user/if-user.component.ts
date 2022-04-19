import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BankAcc } from 'src/models/BankAcc';

@Component({
  selector: 'app-if-user',
  templateUrl: './if-user.component.html',
  styleUrls: ['./if-user.component.css']
})
export class IfUserComponent implements OnInit {
  isVisability: boolean = false;
  bankAcc: BankAcc = {
    ID: 0,//iss
    NAME: 'null',
    LAST_NAME: 'null',
    START_BILL: '00-00-00',
    START_T_BILL: '00:00:00',
    BALANCE: 0,
  }// можно добавить флаг, чтобы не отображался юзер если не передали 
  id:  number = 0;
  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  }

}
