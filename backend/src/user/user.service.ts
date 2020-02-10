import { Injectable, HttpException, Logger } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { User } from '../entities';
import { Rank } from '../entities/user.entity';
import { Repository } from 'typeorm';
import { validate, IsEmpty } from 'class-validator';
import { ClassValidateException } from '../exceptions/ClassValidateException';
import { MongoException } from '../exceptions/MongoException';

@Injectable()
export class UserService {
    constructor(
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>
    ) {}

    findUser(data) : object {
        const rtb = this.UserRepository.find(data)
        .then(res => {
            return {
                statusCode: 0,
                data: res
            }
        })
        .catch(err => {
            return {
                statusCode: 1,
                error: err,
            }
        })

        return rtb
    }

    async createUser(data) : Promise<object> {
        let entry = this.UserRepository.create();

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
            throw new MongoException(err);
        })

        return rtb;
    }

    async updateUser(id, data = {}) : Promise<object> {
        var error = undefined;

        await this.UserRepository.update(id, data)
        .then(res => {
            Logger.log(res, "UpdateUserResult");
        })
        .catch(err => {
            error = err;
        })

        if (error != undefined && IsEmpty(error)) {
            throw new HttpException(`Entry [${id}] not found`, 400);
        } else if (error !== undefined) {
            throw new MongoException(error);
        }

        const updatedUser = this.UserRepository.findOne(id);

        return updatedUser;
    }

    deleteUser(id) : object {
        return {}
    }
}
