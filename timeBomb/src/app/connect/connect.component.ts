import { Component, OnInit, HostListener } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { AppConfigService } from '../app-config.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connect',
  templateUrl: './connect.component.html',
  styleUrls: ['./connect.component.css']
})
export class ConnectComponent implements OnInit {

  user: User = new User();
  isRegistering: boolean = false;
  connectionOk: boolean = null;
  registerOk: boolean = null;

  constructor(public srvUser: UserService, private appConfig: AppConfigService, private router: Router) { }

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
      alert(this.user.name + this.user.username + this.user.password);
      this.srvUser.add(this.user)
          .subscribe(
            user => {
              this.user = user;
            },
            error => {
              this.registerOk = false;
              this.user.username = "";
              this.user.password = "";
            });
      
    } else {
      this.srvUser.user = this.user;
      this.srvUser.connexion(this.user)
          .subscribe(
            user => {
            this.appConfig.setLogin(this.user);
            this.router.navigateByUrl("/home");
            this.srvUser.user.id = user.id;
          },
            error => {
              this.connectionOk = false;
              this.user.password = "";
          });
    }
  }

  register(){
    this.isRegistering = true;
  }
}
