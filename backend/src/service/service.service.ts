import { Injectable, Logger, HttpException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Service } from '../entities';
import { Repository } from 'typeorm';
import { validate } from 'class-validator';
import { ClassValidateException } from '../exceptions/ClassValidateException';
import { MongoException } from '../exceptions/MongoException';

@Injectable()
export class ServiceService {
    constructor(
        @InjectRepository(Service)
        private readonly ServiceRepository : Repository<Service>
    ) {}

    getAll() : object {
        const rtb = this.ServiceRepository.find()
        .then(res => {
            return {
                statusCode: 0,
                data: res
            }
        })
        .catch(err => {
            return {
                statusCode: 503,
                error: err,
            }
        })

        return rtb
    }

    async createNew(data) : Promise<object> {
        if (data.name === null || data.name === undefined) {
            throw new HttpException("Missing name", 400);
        }

        let entry = this.ServiceRepository.create();

        entry.name = data.name;

        const rtb = this.ServiceRepository.save(entry)
        .then(res => {
            return {
                statusCode: 0,
                data: res,
            };
        })
        .catch(err => {
            throw new MongoException(err);
        })

        return rtb
    }
}
