import { Component } from '@angular/core';
import { PixabayResponse } from 'src/app/Models/PixabayResponse.model';
import { PixabayStateService } from 'src/app/Services/pixabay-state.service';
import { PixabayService } from 'src/app/Services/pixabay.service';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.scss']
})
export class SearchbarComponent {

  public inputSearch: string = '';

  constructor(public pixabayStateService: PixabayStateService, private pixabayService: PixabayService){}

  searchImages() {
    this.pixabayStateService.updatePixabayImages([]);
    this.pixabayService.getImagesBySearch(this.inputSearch).subscribe({
      next: (res: PixabayResponse) => {
        this.pixabayStateService.updatePixabayImages(res.hits);
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });   
  }

}
