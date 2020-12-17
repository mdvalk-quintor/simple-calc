import { OperationType } from './operation-type';

export class Operation {
  constructor(
    public operationType: OperationType,
    public description: string,
  ) { }
}
