import { Card } from './card';

export class User {
  constructor(public id?: number,
              public name?: string,
              public username?: string,
              public password?: string,
              public currentRole?: string,
              public currentMatch?: number,
              public hand?: Array<Card>){}
}
