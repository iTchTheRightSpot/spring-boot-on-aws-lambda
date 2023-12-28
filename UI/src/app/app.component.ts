import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {AppService} from "./app.service";
import {FormControl} from "@angular/forms";
import {catchError, debounceTime, distinctUntilChanged, of, switchMap} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  template: `
    <div class="flex justify-center w-full p-2">
      <h1 class="w-fit border-b border-black">
        Testing deploying an Angular and Spring boot project to AWS where API gateway acts as reverse proxy
      </h1>
    </div>

    <div class="w-full bg-green-300">
      <h1 class="p-2">
        Create a new name (note it saves after 1 second of typing)
      </h1>

      <input type="text"
             id="search-bar"
             [formControl]="name"
             class="block p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50"
             placeholder="name">

      <div class="w-full p-2">
        <ul class="p-1">
          <li *ngFor="let n of name$ | async">{{ n.name }}</li>
        </ul>
      </div>

    </div>

    <div class="w-full bg-blue-300">
      <h1 class="p-2">
        Enter any number
      </h1>

      <input type="number"
             id="search-bar"
             [formControl]="formControl"
             class="block p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50"
             placeholder="I'm searching for...">

      <div class="w-full p-2">

        <ng-container *ngIf="search$ | async as result">
          <h1 class="">Search result {{ result.name }}</h1>
        </ng-container>
      </div>

    </div>

    <router-outlet></router-outlet>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent {

  private readonly service = inject(AppService);

  name = new FormControl();
  name$ = this.name.valueChanges
    .pipe(
      distinctUntilChanged(),
      debounceTime(1000),
      switchMap((p: string) => !(p.trim().length > 0)
        ? of()
        : this.service.persist({ name: p })
          .pipe(switchMap(() => this.service.messages()))
      ),
    );

  formControl = new FormControl();
  search$ = this.formControl.valueChanges
    .pipe(
      distinctUntilChanged(),
      debounceTime(700),
      switchMap((p: string) => this.service
        .message(Number(p))
        .pipe(catchError((e: HttpErrorResponse) => of({ name: e.message })))
      ),
    )

}
