import { Component } from '@angular/core';
import { PixabayResponse } from 'src/app/Models/PixabayResponse.model';
import { PixabayStateService } from 'src/app/Services/pixabay-state.service';
import { PixabayService } from 'src/app/Services/pixabay.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  constructor(public pixabayStateService: PixabayStateService, private pixabayService: PixabayService){}

  public categories = [
    { 'categoryName': 'Ciencia', 'value': 'science' },
    { 'categoryName': 'Educación', 'value': 'education' },
    { 'categoryName': 'Personas', 'value': 'people' },
    { 'categoryName': 'Sentimientos', 'value': 'feelings' },
    { 'categoryName': 'Computadoras', 'value': 'computer' },
    { 'categoryName': 'Edificios', 'value': 'buildings' }
  ];
  
  selectCategories(category: string) {
    this.pixabayStateService.updatePixabayImages([]);
    this.pixabayService.getImagesByCategory(category).subscribe({
      next: (res: PixabayResponse) => {
        this.pixabayStateService.updatePixabayImages(res.hits);
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });   
  }

}
