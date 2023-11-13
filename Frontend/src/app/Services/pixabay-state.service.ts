import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { PixabayImage } from 'src/app/Models/PixabayImage.model';

@Injectable({
  providedIn: 'root'
})
export class PixabayStateService {

  private pixabayImagesSubject = new BehaviorSubject<PixabayImage[]>([]);
  pixabayImages$ = this.pixabayImagesSubject.asObservable();

  updatePixabayImages(images: PixabayImage[]) {
    this.pixabayImagesSubject.next(images);
  }

}
