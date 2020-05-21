import { Component, OnInit, HostListener } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-connect',
  templateUrl: './connect.component.html',
  styleUrls: ['./connect.component.css']
})
export class ConnectComponent implements OnInit {

  user: User = new User();
  isRegistering: boolean = false;

  constructor(public srvUser: UserService) { }

  ngOnInit(): void {
  }

  anim(){
    document.getElementById("logo").className = "anim";
  }

  @HostListener ('document: keydown', ['$event']) onKeyDown(e: KeyboardEvent){
    if (e.key == "Enter"){
      this.submit();
    }
  }

  submit(){
    if (this.isRegistering){
      alert("Créer un nouveau compte");
    } else {
      this.srvUser.connexion(this.user);
    }
    
  }

  register(){
    this.isRegistering = true;
  }
}
