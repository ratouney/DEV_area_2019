import { Injectable } from '@nestjs/common';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { Area, Session, Action, Reaction, Service, User } from '../entities';
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
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>,
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

        const rtb = await this.AreaRepository.find({where: {user: userId}, relations: ["action", "action.service", "reaction", "reaction.service"]})
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

    async createArea(token, params) : Promise<object> {
        const ses = await this.SessionRepository.find({where: {token: token}});

        if (ses.length == 0) {
            return {
                statusCode: 403,
                error: "Invalid session token",
            }
        }

        const data = jwt.decode(token, { json: true });
        const userId = data.id;
        
        const user = await this.UserRepository.findOne(userId);

        const action = await this.ActionRepository.find({where: {id: params.actionId}});
        if (action.length == 0) {
            return {
                statusCode: 403,
                error: "Action not found",
            }
        }

        const reaction = await this.ReactionRepository.find({where: {id: params.reactionId}});
        if (reaction.length == 0) {
            return {
                statusCode: 403,
                error: "Reaction not found"
            }
        }

        const entry = await this.AreaRepository.create();
        entry.user = user;
        entry.action = action[0];
        entry.reaction = reaction[0];
        entry.name = params.name;
        entry.timeCheck = params.timeCheck;

        const rtb = await this.AreaRepository.save(entry)
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

    async getActions(serviceId) : Promise<object> {
        if (serviceId == "" ||serviceId == null) {
            return this.getAllActions();
        }
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

    async getAllActions() : Promise<object> {
        const rtb = await this.ActionRepository.find({relations: ["service"]})
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

    async updateAction(id, params) : Promise<object> {
        if (params == {}) {
            return {
                statusCode: 400,
                error: "Nothing to update",
            }
        }

        console.log("Params to update", params);

        let entry = await this.ActionRepository.findOne(id);

        if (entry == null) {
            return {
                statusCode: 400,
                error: "Action not found",
            }
        }

        entry = {
            ...entry,
            ...params,
        };

        const rtb = this.ActionRepository.update(id, entry)
        .then(res => {
            return {
                statusCode: 200,
                data: entry,
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                error: err
            }
        });

        return rtb;
    }

    async getReactions(serviceId) : Promise<object> {
        if (serviceId == "" ||serviceId == null) {
            return this.getAllReactions();
        }

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

    async getAllReactions() : Promise<object> {
        const rtb = await this.ReactionRepository.find({relations: ["service"]})
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

    async updateReaction(id, params) : Promise<object> {
        if (params == {}) {
            return {
                statusCode: 400,
                error: "Nothing to update",
            }
        }

        console.log("Params to update", params);

        let entry = await this.ReactionRepository.findOne(id);

        if (entry == null) {
            return {
                statusCode: 400,
                error: "Reaction not found",
            }
        }

        entry = {
            ...entry,
            ...params,
        };

        const rtb = this.ReactionRepository.update(id, entry)
        .then(res => {
            return {
                statusCode: 200,
                data: entry,
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                error: err
            }
        });

        return rtb;
    }

    async updateAreaRun(id) : Promise<object> {
        console.log("DateNow : ", Date.now().toString());
        const val = Date.now().toString();

        this.AreaRepository.update(id, {
            lastRun: val,
        });
        
        return {}
    }

    async checkAreaShouldRun(id) : Promise<object> {
        const entry = await this.AreaRepository.findOne(id);

        const dt = new Date(parseInt(entry.lastRun));
        const n = new Date();

        const diff = n.getMinutes() + (n.getHours() * 60) - (dt.getHours() * 60) - dt.getMinutes();

        return {
            lastRun: entry.lastRun,
            interval: entry.timeCheck,
            diff: diff,
            shouldRun: diff > entry.timeCheck,
        };
    }
}
