import { Injectable, Logger } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Token, Session, Service, User } from '../entities';
import { Repository } from 'typeorm';
import jwt from 'jsonwebtoken';

@Injectable()
export class TokenService {
    constructor(
        @InjectRepository(Token)
        private readonly TokenRepository : Repository<Token>,
        @InjectRepository(Session)
        private readonly SessionRepository : Repository<Session>,
        @InjectRepository(Service)
        private readonly ServiceRepository : Repository<Service>,
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>
    ) {}

    async getTokensFromUser(token) : Promise<object> {
        const ses = await this.SessionRepository.find({where: {token: token}});

        if (ses.length == 0) {
            return {
                statusCode: 403,
                error: "Invalid session token",
            }
        }

        const data = jwt.decode(token, { json: true});
        const userId = data.id;
        Logger.log(data, "TOkenDATA");

        const rtb = await this.TokenRepository.find({where: {user: {id: userId}}, relations: ["service"]})
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

        return rtb;
    }

    async createToken(authToken, params) : Promise<object> {
        const ses = await this.SessionRepository.find({where: {token: authToken}});

        if (ses.length == 0) {
            return {
                statusCode: 403,
                error: "Invalid session token",
            }
        }
8
        const data = jwt.decode(authToken, { json: true});
        const userId = data.id;

        const service = await this.ServiceRepository.find({where: {id: params.serviceId}});
        if (service.length == 0) {
            return {
                statusCode: 403,
                error: "Service not found",
            }
        }

        const user = await this.UserRepository.findOne(userId);

        const entry = this.TokenRepository.create();
        entry.service = service[0];
        entry.token = params.token;
        entry.user = user;
        
        const rtb = await this.TokenRepository.save(entry)
        .then(res => {
            return {
                statusCode: 201,
                data: res
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                error: err,
            }
        })

        return rtb;
    }

}
