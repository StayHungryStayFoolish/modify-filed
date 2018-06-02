import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { CarMySuffixComponent } from './car-my-suffix.component';
import { CarMySuffixDetailComponent } from './car-my-suffix-detail.component';
import { CarMySuffixPopupComponent } from './car-my-suffix-dialog.component';
import { CarMySuffixDeletePopupComponent } from './car-my-suffix-delete-dialog.component';

@Injectable()
export class CarMySuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const carRoute: Routes = [
    {
        path: 'car-my-suffix',
        component: CarMySuffixComponent,
        resolve: {
            'pagingParams': CarMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'modifyFiledApp.car.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'car-my-suffix/:id',
        component: CarMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'modifyFiledApp.car.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const carPopupRoute: Routes = [
    {
        path: 'car-my-suffix-new',
        component: CarMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'modifyFiledApp.car.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'car-my-suffix/:id/edit',
        component: CarMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'modifyFiledApp.car.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'car-my-suffix/:id/delete',
        component: CarMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'modifyFiledApp.car.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
