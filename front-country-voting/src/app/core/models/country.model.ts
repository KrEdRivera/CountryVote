export interface CountryResponse {
  name: {
    common: string;
  };
  capital?: string[];
  region: string;
  subregion: string;
}


export interface Country {
  name: string;
  capital: string;
  region: string;
  subRegion: string;
}

export interface RankingResponse {
  country: string;
  capital: string;
  region: string;
  subregion: string;
  votes: number;
}

export interface VoteRequest {
  username: string;
  email: string;
  country: string;
  region: string;
  subRegion: string;
  capital: string;
}

export interface RankingPageResponse {
  content: RankingResponse[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  size: number;
  totalElements: number;
  totalPages: number;
}