import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CarMySuffix } from './car-my-suffix.model';
import { CarMySuffixPopupService } from './car-my-suffix-popup.service';
import { CarMySuffixService } from './car-my-suffix.service';

@Component({
    selector: 'jhi-car-my-suffix-dialog',
    templateUrl: './car-my-suffix-dialog.component.html'
})
export class CarMySuffixDialogComponent implements OnInit {

    car: CarMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private carService: CarMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.car.id !== undefined) {
            this.subscribeToSaveResponse(
                this.carService.update(this.car));
        } else {
            this.subscribeToSaveResponse(
                this.carService.create(this.car));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CarMySuffix>>) {
        result.subscribe((res: HttpResponse<CarMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CarMySuffix) {
        this.eventManager.broadcast({ name: 'carListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-car-my-suffix-popup',
    template: ''
})
export class CarMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private carPopupService: CarMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.carPopupService
                    .open(CarMySuffixDialogComponent as Component, params['id']);
            } else {
                this.carPopupService
                    .open(CarMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
