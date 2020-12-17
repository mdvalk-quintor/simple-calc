import {AfterViewInit, Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {CalculationService} from '../shared/service/calculation.service';
import {Calculation} from '../shared/model/calculation';
import {OperationType} from '../shared/model/operation-type';
import {Operation} from '../shared/model/operation';

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

  constructor(
    private calculationService: CalculationService,
    private formBuilder: FormBuilder,
  ) {
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

  reset(): void {
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
