import { Component, OnInit, AfterViewInit, ViewChild, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCard, MatCardContent } from '@angular/material/card';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { Country, RankingResponse } from '../../../core/models/country.model';
import { VotingService } from '../../../core/services/voting.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';


@Component({
  standalone: true,
  selector: 'app-countries-table',
  imports: [
    CommonModule,
    MatCard,
    MatTableModule,
    MatSortModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule
  ],
  templateUrl: './countries-table.component.html',
  styleUrls: ['./countries-table.component.scss']
})
export class CountriesTableComponent implements OnInit, AfterViewInit, OnDestroy {
  displayedColumns = [
    'country',
    'capital',
    'region',
    'subregion',
    'votes'
  ];

  dataSource = new MatTableDataSource<RankingResponse>([]);
  private destroy$ = new Subject<void>();

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private votingService: VotingService) {}

  ngOnInit(): void {
    this.loadVoteRanking();
    
    // Suscribirse a los votos exitosos
    this.votingService.voteSubmitted$
      .pipe(takeUntil(this.destroy$))
      .subscribe(() => {
        this.loadVoteRanking();
      });
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private loadVoteRanking(): void {
    this.votingService.getVoteRanking().subscribe({
      next: (response) => {
        this.dataSource.data = response.content;
      },
      error: (error) => {
        console.error("Error fetching vote ranking:", error);
      }
    });
  }

  applyFilter(event: Event): void {
    const value = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value.trim().toLowerCase();
  }
}
