import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ConnectComponent } from './connect/connect.component';
import { HomeComponent } from './home/home.component';
import { GameComponent } from './game/game.component';
import { PlayerPipe } from './player.pipe';

const routes: Routes = [
	{path: 'connect', component: ConnectComponent},
	{path: 'home', component: HomeComponent},
	{path: 'game', component: GameComponent},
	{path: '', redirectTo: 'connect', pathMatch: 'full'},
	{path: '**', redirectTo: 'connect', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    ConnectComponent,
    HomeComponent,
    GameComponent,
    PlayerPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'}),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
