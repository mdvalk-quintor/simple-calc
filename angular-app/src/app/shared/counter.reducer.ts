import { Action, createReducer, on } from '@ngrx/store';
import { click, decrement, increment, reset } from './counter.actions';

export const initialState = 0;

const counterReducerFunc = createReducer<number, Action>(
  initialState,
  on(increment, (state) => state + 1),
  on(decrement, (state) => state - 1),
  on(reset, (state) => 0)
);

export function counterReducer(state: number | undefined, action: Action): number {
  return counterReducerFunc(state, action);
}

const clickReducerFunc = createReducer<number, Action>(
  initialState,
  on(click, (state) => state + 1)
);

export function clickReducer(state: number | undefined, action: Action): number {
  return clickReducerFunc(state, action);
}
/*
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://github.com/ngrx/platform
*/
