import { Injectable, Logger, HttpException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Token, Service, User } from '../entities';
import { Repository, getRepository, getMongoRepository } from 'typeorm';
import { MongoException } from '../exceptions/MongoException';
import { ObjectID } from 'mongodb';

@Injectable()
export class TokenService {
    constructor(
        @InjectRepository(Token)
        private readonly TokenRepository : Repository<Token>,
        @InjectRepository(Service)
        private readonly ServiceRepository : Repository<Service>,
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>,
    ) {}

    async listTokens(params) : Promise<object> {
        console.log("List tokens of user : ", params.userId);
        let tokens = await this.TokenRepository.find({where:  {userId: new ObjectID(params.userId)}})
        .then(res => {
            console.log("res :", res);
            return res;
        })
        .catch(err => {
            console.log("err : ", err);
            return err;
        })

        return tokens;
    }

    async createToken(data) : Promise<object> {
        let user = await this.UserRepository.findOne(data.userId)
        .then(res => {
            return {
                statusCode: 200,
                data: res
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                err: err.toString(),
                data: null
            }
        })

        if (user.statusCode == 400) {
            return user;
        }
        if (user.data == null) {
            return {
                statusCode: 400,
                msg: `User [${data.userId}] not found`
            }
        }

        let service = await this.ServiceRepository.findOne(data.serviceId)
        .then(res => {
            return {
                statusCode: 200,
                data: res
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                err: err.toString(),
                data: null
            }
        })

        if (service.statusCode == 400) {
            return service;
        }
        if (service.data == null) {
            return {
                statusCode: 400,
                msg: `Service [${data.serviceId}] not found`
            }
        }

        let entry = this.TokenRepository.create();
        entry.data = data.token;
        entry.userId = user.data.id;
        entry.serviceId = service.data.id;

        const rtb = this.TokenRepository.save(entry)
        .then(res => {
            return {
                statusCode: 201,
                data: res,
            };
        })
        .catch(err => {
            throw new MongoException(err);
        })

        return rtb
    }
}
