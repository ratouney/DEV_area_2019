import { Injectable, Logger } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from '../entities';
import { Repository } from 'typeorm';
import { Rank } from '../entities/user.entity';
import { validate } from 'class-validator';
import { ClassValidateException } from '../exceptions/ClassValidateException';

@Injectable()
export class UserService {
    constructor(
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>
    ) {}

    getAllUsers() : object {
        const rtb = this.UserRepository.find()
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

    getUser(id) : object {
        const rtb = this.UserRepository.findOne(id)
        .then(res => {
            if (res == undefined) {
                return {
                    statusCode: 400,
                    err: `User [${id}] doesn't exist`,
                    data: null,
                }
            }
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

    async createUser(data) : Promise<object> {
        let entry = this.UserRepository.create()

        entry = { ...data, rank: Rank.user};

        const errors = await validate(entry)
        if (errors.length > 0) {
            throw new ClassValidateException(errors);
        }

        const rtb = this.UserRepository.save(entry)
        .then(res => {
            return {
                statusCode: 201,
                data: res,
            };
        })
        .catch(err => {
            return  {
                statusCode: 400,
                err: err,
                data: null
            }
        })

        return rtb;
    }

    async updateUser(id, data) : Promise<object> {
        const rtb = this.UserRepository.update(id, data)
        .then(res => {
            return res;
        })
        .catch(err => {
            return err;
        })

        return rtb;
    }
}
