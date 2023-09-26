import {ChangeDetectionStrategy, Component, DestroyRef, inject} from '@angular/core';
import {AppService} from "./service/app.service";
import {FormBuilder, FormControl} from "@angular/forms";
import {Message} from "./message";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";
import {tap} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent {

  private readonly fb: FormBuilder = inject(FormBuilder);
  private readonly destroyRef: DestroyRef = inject(DestroyRef);
  private readonly appService: AppService = inject(AppService);

  messages$ = this.appService.messages();
  sse$ = this.appService.sse();

  form = this.fb.group({
    name: new FormControl(''),
  })

  submit(): void {
    const name = this.form.controls['name'].value;
    const message: Message = { name: name ? name : '' };
    this.appService.persist(message)
      .pipe(
        tap(() => this.form.reset()),
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe();
  }

}
