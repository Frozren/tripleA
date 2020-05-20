import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { ConnectComponent } from './connect/connect.component';
import { HomeComponent } from './home/home.component';
import { GameComponent } from './game/game.component';

const routes: Routes = [
	{path: 'home', component: HomeComponent},
	{path: 'game', component: GameComponent},
	{path: '', redirectTo: 'home', pathMatch: 'full'},
	{path: '**', redirectTo: 'home', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    ConnectComponent,
    HomeComponent,
    GameComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes, {scrollPositionRestoration: 'enabled'})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
