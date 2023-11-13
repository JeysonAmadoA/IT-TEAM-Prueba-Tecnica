import { Component, OnInit } from '@angular/core';
import { PixabayImage } from 'src/app/Models/PixabayImage.model';
import { PixabayResponse } from 'src/app/Models/PixabayResponse.model';
import { PixabayService } from 'src/app/Services/pixabay.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { PixabayStateService } from 'src/app/Services/pixabay-state.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})

export class ContentComponent implements OnInit {

  public pixabayImages: PixabayImage[] = [];
  public modalRef: BsModalRef | undefined;

  constructor(
    private pixabayService: PixabayService,
    private modalService: BsModalService,
    public pixabayStateService: PixabayStateService
    ){}

  openModal(template: any) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit(): void {
    this.pixabayService.getAll().subscribe({
      next: (res: PixabayResponse) => {
        this.pixabayStateService.updatePixabayImages(res.hits);
        console.log(res.hits);
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });
  }
  

}
