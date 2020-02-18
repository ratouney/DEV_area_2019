import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Area, Session, Action, Reaction, Service } from '../entities';
import jwt from 'jsonwebtoken';

@Injectable()
export class AreaService {
    constructor(
        @InjectRepository(Area)
        private readonly AreaRepository : Repository<Area>,
        @InjectRepository(Session)
        private readonly SessionRepository : Repository<Session>,
        @InjectRepository(Action)
        private readonly ActionRepository : Repository<Action>,
        @InjectRepository(Reaction)
        private readonly ReactionRepository : Repository<Reaction>,
        @InjectRepository(Service)
        private readonly ServiceRepository : Repository<Service>,
    ) {}

    async getAreas(token) : Promise<object> {
        const ses = await this.SessionRepository.find({where: {token: token}});

        if (ses.length == 0) {
            return {
                statusCode: 403,
                error: "Invalid session token",
            }
        }

        const data = jwt.decode(token, { json: true});
        const userId = data.id;

        const rtb = await this.AreaRepository.find({where: {user: userId}})
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

    async getActions(serviceId) : Promise<object> {
        const rtb = await this.ActionRepository.find({where: {service: {id: serviceId}}})
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

    async createAction(params) : Promise<object> {
        const service = await this.ServiceRepository.find({where: {id: params.serviceId}});
        if (service.length == 0) {
            return {
                statusCode: 403,
                error: "Service not found",
            }
        }

        const entry = this.ActionRepository.create();
        entry.service = service[0];
        entry.name = params.name;
        entry.description = params.description;

        const rtb = await this.ActionRepository.save(entry)
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

    async getReactions(serviceId) : Promise<object> {
        const rtb = await this.ReactionRepository.find({where: {service: {id: serviceId}}})
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

    async createReaction(params) : Promise<object> {
        if (params.serviceId === "") {
            return {
                statusCode: 400,
                error: "Service missing",
            }
        }

        const service = await this.ServiceRepository.find({where: {id: params.serviceId}});
        if (service.length == 0) {
            return {
                statusCode: 403,
                error: "Service not found",
            }
        }

        console.log("MERDE", service);

        const entry = this.ReactionRepository.create();
        entry.service = service[0];
        entry.name = params.name;
        entry.description = params.description;

        const rtb = await this.ReactionRepository.save(entry)
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
