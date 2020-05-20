import { Component, OnInit } from '@angular/core';
import { User } from '../user';

@Component({
  selector: 'app-connect',
  templateUrl: './connect.component.html',
  styleUrls: ['./connect.component.css']
})
export class ConnectComponent implements OnInit {

  user: User = new User();
  test: string = "oui";

  constructor() { }

  ngOnInit(): void {
  }

  submit(){
    alert(this.user.name + " - " + this.user.username + " - " + this.user.password);
  }

}
