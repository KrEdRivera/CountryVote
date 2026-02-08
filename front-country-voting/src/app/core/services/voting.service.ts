import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Country, VoteRequest, RankingResponse, RankingPageResponse } from '../models/country.model';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VotingService {
  private readonly baseUrl = environment.backUrl;
  private voteSubmittedSubject = new Subject<VoteRequest>();
  public voteSubmitted$ = this.voteSubmittedSubject.asObservable();
  
  constructor(private http: HttpClient) {}
  
  submitVote(voteData: VoteRequest): Observable<string> {
    const url = `${this.baseUrl}/api/v1/votes`;
    return this.http.post(url, voteData, { responseType: 'text' });
  }

  notifyVoteSubmitted(voteData: VoteRequest): void {
    this.voteSubmittedSubject.next(voteData);
  }

  getVoteRanking(): Observable<RankingPageResponse> {
    const url = `${this.baseUrl}/api/v1/votes/ranking`;
    return this.http.get<RankingPageResponse>(url);
  }

}