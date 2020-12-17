import { HttpHeaders } from '@angular/common/http';

export class HttpUtil {

  public static get getHeaders(): { headers: HttpHeaders } {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
  }
}
