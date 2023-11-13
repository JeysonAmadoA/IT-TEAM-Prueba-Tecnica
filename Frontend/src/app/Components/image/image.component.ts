import { Component, Input } from '@angular/core';
import { PixabayImage } from 'src/app/Models/PixabayImage.model';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss']
})
export class ImageComponent {

  @Input() pixabayImage!: PixabayImage;


}
