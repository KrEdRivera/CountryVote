import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Country, CountryResponse } from '../../../app/core/models/country.model';



@Injectable({
  providedIn: 'root'
})
export class CountryService {
  private readonly baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getCountries() {
    const url = `${this.baseUrl}/all?fields=name,capital,region,subregion`;
    return this.http.get<CountryResponse[]>(url)
    .pipe(
        map(reponse => this.mapToCountries(reponse))
    );
  }

  private mapToCountries(response: CountryResponse[]): Country[] {
    return response.map(country => ({
      name: country.name.common,
      capital: country.capital ? country.capital[0] : 'N/A',
      region: country.region,
      subRegion: country.subregion
    }))
    .sort((a, b) => a.name.localeCompare(b.name));
  }
}
