import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PixabayResponse } from '../Models/PixabayResponse.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PixabayService {

  private apiUrl = `https://pixabay.com/api/?key=${environment.apiKey}`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<PixabayResponse> {
    return this.http.get<PixabayResponse>(this.apiUrl);
  }

  getImagesByCategory(category:string): Observable<PixabayResponse> {
    let categoryApiUrl : string = `${this.apiUrl}&category=${category}` 
    return this.http.get<PixabayResponse>(categoryApiUrl);
  }

  getImagesBySearch(searchEntry:string): Observable<PixabayResponse> {
    let searchApiUrl : string = `${this.apiUrl}&lang=es&q=${searchEntry}`;
    return this.http.get<PixabayResponse>(searchApiUrl);
  }
}
