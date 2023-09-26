import {inject, Injectable, NgZone} from '@angular/core';
import {map, Observable, Subscriber} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Message} from "../message";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private DOMAIN = 'http://localhost:8080/'

  private readonly http: HttpClient = inject(HttpClient);
  private readonly zone: NgZone = inject(NgZone);

  persist(payload: Message): Observable<unknown> {
    return this.http.post<Message>(this.DOMAIN, payload, {
      headers: { 'content-type': 'application/json' },
      observe: 'response'
    });
  }

  messages(): Observable<Message[]> {
    return this.http.get<Message[]>(this.DOMAIN, {
      headers: { 'content-type': 'application/json' },
      observe: 'response'
    }).pipe(map((res: HttpResponse<Message[]>): Message[] => res.body === null ? [] : res.body));
  }

  sse(): Observable<Message[]> {
    return new Observable((observer: Subscriber<Message[]>): void => {
      const eventSource = new EventSource(this.DOMAIN + 'sse');

      eventSource.onmessage = (event: MessageEvent<any>): void => {
        this.zone.run(() => observer.next(JSON.parse(event.data)))
      };

      eventSource.onerror = (error: Event): void => {
        if (error.eventPhase !== EventSource.CLOSED) {
          this.zone.run(() => observer.error(error));
        }
      }
    });
  }

}
