import { Component } from '@angular/core';
import { VoteFormComponent } from './vote-form/vote-form.component';
import { CountriesTableComponent } from './countries-table/countries-table.component';

@Component({
  standalone: true,
  imports: [VoteFormComponent, CountriesTableComponent],
  template: `
    <app-vote-form class="mb-5"></app-vote-form>
    <app-countries-table></app-countries-table>
  `
})
export class VotingPage {}