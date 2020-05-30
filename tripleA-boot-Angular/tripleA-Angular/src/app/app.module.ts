import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BattleFieldComponent } from './battle-field/battle-field.component';
import { ChoiceComponent } from './choice/choice.component';
import { ConnectComponent } from './connect/connect.component';
import { HomeComponent } from './home/home.component';
import { MatchupComponent } from './matchup/matchup.component';
import { NewGameComponent } from './new-game/new-game.component';
import { OptionsComponent } from './options/options.component';
import { PrologueComponent } from './prologue/prologue.component';
import { SaveComponent } from './save/save.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'prologue', component: PrologueComponent },
  { path: 'connect', component: ConnectComponent },
  { path: 'options', component: OptionsComponent },
  { path: 'newGame', component: NewGameComponent },
  { path: 'save', component: SaveComponent },
  { path: 'matchup', component: MatchupComponent },
  { path: 'choice', component: ChoiceComponent },
  { path: 'battleField', component: BattleFieldComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: HomeComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    BattleFieldComponent,
    ChoiceComponent,
    ConnectComponent,
    HomeComponent,
    MatchupComponent,
    NewGameComponent,
    OptionsComponent,
    PrologueComponent,
    SaveComponent
  ],
  imports: [
    BrowserModule, FormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
