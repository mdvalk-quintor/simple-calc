import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {TestBed} from '@angular/core/testing';
import {CalculationService} from './calculation.service';
import {Calculation} from '../model/calculation';
import {OperationType} from '../model/operation-type';

describe('Service Tests', () => {
  describe('Calculation Service', () => {

    let httpTestingController: HttpTestingController;
    let calculationService: CalculationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [CalculationService],
      });

      httpTestingController = TestBed.inject(HttpTestingController);
      calculationService = TestBed.inject(CalculationService);
    });

    it('should be created', () => {
      expect(calculationService).toBeTruthy();
    });

    it('should send a calculation', () => {
      const calculation = new Calculation(
        1330,
        7,
        OperationType.ADDITION,
        0);

      calculationService.send(calculation).subscribe(res => expect(res).toBe(calculation));

      const req = httpTestingController.expectOne(CalculationService.url);
      expect(req.request.method).toBe('POST');
      expect(req.request.body).toBe(JSON.stringify(calculation));
      expect(req.request.headers.get('Content-type')).toBe('application/json');

      req.flush(calculation);
      httpTestingController.verify();
    });

  });
});
