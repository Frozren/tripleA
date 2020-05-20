import { Match } from './match';
import { User } from './user';

export class Card {
  constructor(public id?: number,
              public type?: string,
              public revealed?: boolean,
              public match?: Match,
              public owner?: User){}
}
