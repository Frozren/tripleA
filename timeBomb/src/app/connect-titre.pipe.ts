import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'connectTitre'
})
export class ConnectTitrePipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (args[0] == "titre"){
      if (value){
        return "Inscription";
      } else {
        return "Connexion";
      }
    }
    
    if (args[0] == "submit"){
      if (value){
        return "S'inscrire";
      } else {
        return "Se connecter";
      }
    }
  }

}
