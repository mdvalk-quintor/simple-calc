import {ComponentFixture, TestBed} from '@angular/core/testing';
import {FormBuilder, ReactiveFormsModule} from '@angular/forms';
import {of, throwError} from 'rxjs';

import {CalculatorComponent} from './calculator.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {CalculationService} from '../shared/service/calculation.service';
import {Calculation} from '../shared/model/calculation';
import {OperationType} from '../shared/model/operation-type';

describe('Component Tests', () => {
  describe('CalculatorComponent', () => {
    let fixture: ComponentFixture<CalculatorComponent>;
    let component: CalculatorComponent;
    let mockCalculationService: CalculationService;

    const calculation: Calculation = new Calculation(3, 3, OperationType.DIVISION, 0);

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule, ReactiveFormsModule],
        declarations: [CalculatorComponent],
        providers: [FormBuilder, CalculationService],
      })
        .compileComponents();

      mockCalculationService = TestBed.inject(CalculationService);
      fixture = TestBed.createComponent(CalculatorComponent);
      component = fixture.componentInstance;
    });

    it('should handle a valid event', () => {

      spyOn(component.calculatorForm, 'reset');
      spyOn(mockCalculationService, 'send').and.returnValue(of(calculation));

      component.onSubmit(calculation);

      expect(component.calculatorForm).toBeTruthy();
      expect(component.calculatorForm.reset).toHaveBeenCalled();
      expect(mockCalculationService.send).toHaveBeenCalledWith(calculation);
    });

    it('should handle an invalid event', () => {
      spyOn(component.calculatorForm, 'reset');
      spyOn(mockCalculationService, 'send').and.returnValue(throwError({status: 404}));

      component.onSubmit(calculation);

      expect(component.calculatorForm).toBeTruthy();
      expect(component.calculatorForm.reset).toHaveBeenCalled();
      expect(mockCalculationService.send).toHaveBeenCalledWith(calculation);
    });


    it('should set calculations when getting calculations succeeded', () => {
      const calculations: Calculation[] = [calculation];
      spyOn(mockCalculationService, 'getCalculations').and.returnValue(of(calculations));

      component.getCalculations();

      expect(component.calculations).toBeTruthy();
      expect(component.calculations.length).toBe(calculations.length);
      expect(mockCalculationService.getCalculations).toHaveBeenCalled();
    });

    it('should not set calculations when getting calculations failed', () => {
      spyOn(mockCalculationService, 'getCalculations').and.returnValue(throwError({status: 404}));

      component.getCalculations();

      expect(component.calculations).toBeTruthy();
      expect(component.calculations.length).toBe(0);
      expect(mockCalculationService.getCalculations).toHaveBeenCalled();
    });

    it('should set calculations upon view initialization', () => {
      const calculations: Calculation[] = [calculation];
      spyOn(mockCalculationService, 'getCalculations').and.returnValue(of(calculations));

      component.ngAfterViewInit();

      expect(component.calculations).toBeTruthy();
      expect(component.calculations.length).toBe(calculations.length);
      expect(mockCalculationService.getCalculations).toHaveBeenCalled();
    });

    it('should reset the calculator page upon calling reset', () => {
      spyOn(component.calculatorForm, 'reset');

      component.reset();

      expect(component.calculatorForm.reset).toHaveBeenCalled();
    });

  });
});
