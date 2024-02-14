import {inject, Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Message} from "./message";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private readonly domain: string | undefined = environment.domain;
  private readonly http = inject(HttpClient);

  header = { 'content-type': 'application/json' }

  persist = (payload: Message): Observable<number> => this.http
    .post<Message>(`${this.domain}`, payload, { headers: this.header, observe: 'response' })
    .pipe(map((res: HttpResponse<Message>) => res.status));

  messages = (): Observable<Message[]> => this.http
    .get<Message[]>(`${this.domain}`, { headers: this.header, observe: 'response' })
    .pipe(map((res: HttpResponse<Message[]>): Message[] => res.body === null ? [] : res.body));

  message = (index: number): Observable<Message> => this.http
    .get<Message>(`${this.domain}/${index}`);

}
