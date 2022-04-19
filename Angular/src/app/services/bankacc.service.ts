import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BankAcc } from 'src/models/BankAcc';
import { JsonPipe } from '@angular/common';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application-json',
    //'content-type': 'text/plaincharset=UTF-8'
  })
}

@Injectable({
  providedIn: 'root'
})
export class BankaccService {
  
  todosUrl = 'http://localhost:48818/endpoint2';

  jsonPipe: JsonPipe = new JsonPipe;

  constructor(private http: HttpClient) { }

  toJSON(bankAcc: BankAcc) {

    const json: string = 
      "{" +
      "\"ID\": \"" + bankAcc.ID + "\","+
      "\"NAME\": \"" + bankAcc.NAME + "\"," +
      "\"LAST_NAME\": \"" + bankAcc.LAST_NAME + "\"," +
      "\"START_BILL\": \"" + "2022-04-15" + "\"," +
      "\"START_T_BILL\": \"" + "21:59:17" + "\"," +
      "\"BALANCE\": \"" + bankAcc.BALANCE + "\" }";
    
    return json;
  }


  postBankAcc(bankAcc: BankAcc): Observable<BankAcc> {// должен закидывать айди имя фамилию
    console.log(this.jsonPipe.transform(bankAcc));// НИКОГДА НЕ ИСПОЛЬЗОВАТЬ ЭТО!!!!!!!!!!!
    return this.http.post<any>(this.todosUrl, this.toJSON(bankAcc));//возможно передаем только 3 аргумента
  }

  deleteBankAcc(id: number) {//должен удалять аккаунт по айди
    console.log(id);
    return this.http.delete(this.todosUrl + '//?id=' + id,httpOptions);
    
  }
  getBankAcc(id: number): Observable<BankAcc> {// возвращает фулл банк аккаунт
    console.log('i am in get');
    return this.http.get<any>(this.todosUrl + '//?id=' + id);
  }
  putBankAcc(bankAcc: BankAcc): Observable<BankAcc> {//изменяет балланс
    return this.http.put<any>(this.todosUrl + '//?id=' + bankAcc.ID, this.toJSON(bankAcc),httpOptions);
  }
  
}
