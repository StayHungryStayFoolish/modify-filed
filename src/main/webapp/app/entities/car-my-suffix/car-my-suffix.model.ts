import { BaseEntity } from './../../shared';

export class CarMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public price?: number,
        public createdAt?: number,
    ) {
    }
}
