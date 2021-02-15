import {AfterViewInit, Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {CalculationService} from '../shared/service/calculation.service';
import {Calculation} from '../shared/model/calculation';
import {OperationType} from '../shared/model/operation-type';
import {Operation} from '../shared/model/operation';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { click, decrement, increment, reset } from '../shared/counter.actions';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.scss']
})
export class CalculatorComponent implements AfterViewInit {

  calculatorForm = this.formBuilder.group({
    numberOne: ['', Validators.required],
    numberTwo: ['', Validators.required],
    operationType: ['', Validators.required],
  });

  calculations: Calculation[] = [];

  operations: Operation[] = [
    new Operation(OperationType.ADDITION, 'Optellen'),
    new Operation(OperationType.SUBSTRACTION, 'Aftrekken'),
    new Operation(OperationType.DIVISION, 'Delen'),
    new Operation(OperationType.MULTIPLICATION, 'Vermenigvuldigen'),
  ];

  count$: Observable<number>;
  clicks$: Observable<number>;

  constructor(
    private calculationService: CalculationService,
    private formBuilder: FormBuilder,
    private store: Store<{ count: number, clicks: number }>
  ) {
    this.count$ = store.select('count');
    this.clicks$ = store.select('clicks');
  }

  onSubmit(eventData: any): void {

    const calculation: Calculation = new Calculation(
      eventData.numberOne,
      eventData.numberTwo,
      eventData.operationType,
      0);

    this.calculationService.send(calculation).subscribe(
      (data: Calculation) => {
        this.getCalculations();
      },
        (error: string) => {
        console.warn('response error: ' + error);
      }
    );

    this.reset();
  }


  increment(): void {
    this.store.dispatch(increment());
    this.store.dispatch(click());
  }

  decrement(): void {
    this.store.dispatch(decrement());
    this.store.dispatch(click());
  }

  reset(): void {
    this.store.dispatch(reset());
    this.store.dispatch(click());
    this.calculatorForm.reset();
  }

  getCalculations(): void {
    this.calculationService.getCalculations().subscribe(
      (calculations: Calculation[]) => {
        this.calculations = calculations;
      },
        (error: any) => {
        console.warn(error);
      }
    );
  }

  getOperatorByType(operationType: OperationType): string {
    switch (operationType) {
      case OperationType.ADDITION: return '+';
      case OperationType.DIVISION: return '/';
      case OperationType.MULTIPLICATION: return '*';
      case OperationType.SUBSTRACTION: return '-';
    }
  }

  ngAfterViewInit(): void {
    this.getCalculations();
  }
}
