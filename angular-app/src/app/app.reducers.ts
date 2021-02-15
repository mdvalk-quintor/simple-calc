import { Action, ActionReducer } from '@ngrx/store';
import { clickReducer, counterReducer } from './shared/counter.reducer';

export class Reducers {
  count: ActionReducer<number, Action> = counterReducer;
  clicks: ActionReducer<number, Action> = clickReducer;
}
