import { Component, OnInit, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCard, MatCardContent } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelect, MatOption } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { VotingService } from '../../../core/services/voting.service';
import { CountryService } from '../../../core/services/country.service';
import { Country, VoteRequest } from '../../../core/models/country.model';

@Component({
  standalone: true,
  selector: 'app-vote-form',
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatCard, MatCardContent, MatFormFieldModule, MatSelect, MatOption, MatButtonModule, MatIconModule],
  templateUrl: './vote-form.component.html',
  styleUrls: ['./vote-form.component.scss']
})
export class VoteFormComponent implements OnInit {
  voteForm: FormGroup;
  selectCountries: Country[] = [];
  showMessage: boolean = false;
  messageText: string = '';
  messageType: 'success' | 'error' = 'success';
  patternEmail: string = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

  constructor(
    private fb: FormBuilder,
    private votingService: VotingService,
    private countryService: CountryService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.voteForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email, Validators.pattern(this.patternEmail)]],
      country: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    console.log('VoteFormComponent ngOnInit called');

    this.countryService.getCountries().subscribe({
      next: (countries) => {
        this.selectCountries = countries;
        console.log("Countries: ", this.selectCountries);
      },
      error: (error) => {
        console.error("Error fetching countries:", error);
      },
      complete: () => {
        console.log("Countries request completed");
      }
    });
  }

  onSubmit(): void {
    if (this.voteForm.valid) {
      const selectedCountry = this.selectCountries.find(c => c.name === this.voteForm.get('country')?.value);
      
      if (selectedCountry) {
        const voteRequest: VoteRequest = {
          username: this.voteForm.get('name')?.value,
          email: this.voteForm.get('email')?.value,
          country: selectedCountry.name,
          region: selectedCountry.region,
          subRegion: selectedCountry.subRegion,
          capital: selectedCountry.capital
        };

        this.votingService.submitVote(voteRequest).subscribe({
          next: (response) => {
            console.log("Vote submitted successfully:", response);
            this.messageText = 'Your vote was successfully submitted';
            this.messageType = 'success';
            this.showMessage = true;
            this.votingService.notifyVoteSubmitted(voteRequest);
            this.resetMessageAfterDelay();
          },
          error: (error) => {
            console.error("Error submitting vote:", error);
            this.messageText = 'Error submitting your vote. Please try again.';
            this.messageType = 'error';
            this.showMessage = true;
            this.resetMessageAfterDelay();
          }
        });
      }
    }
  }

  private resetMessageAfterDelay(): void {
    setTimeout(() => {
      this.showMessage = false;
      if (this.messageType === 'success') {
        this.voteForm.reset();
      }
    }, 3000);
  }
}