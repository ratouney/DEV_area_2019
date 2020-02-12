import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Service } from '../entities';
import { Repository } from 'typeorm';

@Injectable()
export class ServiceService {
    constructor(
        @InjectRepository(Service)
        private readonly ServiceRepository : Repository<Service>
    ) {}

    getAllServices() : object {
        const rtb = this.ServiceRepository.find()
        .then(res => {
            return {
                statusCode: 200,
                data: res
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                error: err,
            }
        })

        return rtb   
    }

    createNewService(params) : object {
        let entry = this.ServiceRepository.create();

        entry.name = params.name;

        const rtb = this.ServiceRepository.save(entry)
        .then(res => {
            return {
                statusCode: 0,
                data: res,
            };
        })
        .catch(err => {
            return {
                statusCode: 400,
                error: err
            }
        })

        return rtb
    }
}
