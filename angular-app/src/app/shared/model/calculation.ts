import { OperationType } from './operation-type';

export class Calculation {
  constructor(
    public numberOne: number,
    public numberTwo: number,
    public operationType: OperationType,
    public result: number,
  ) { }
}
