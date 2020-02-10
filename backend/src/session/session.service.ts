import { Injectable, HttpException, Logger } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Session, User } from '../entities';
import { Repository } from 'typeorm';
import jwt from 'jsonwebtoken';

@Injectable()
export class SessionService {
    constructor(
        @InjectRepository(Session)
        private readonly SessionRepository : Repository<Session>,
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>
    ) {}

    findSession(data) : object {
        const rtb = this.SessionRepository.find(data)
        .then(res => {
            return {
                statusCode: 200,
                data: res
            }
        })
        .catch(err => {
            return {
                status: 400,
                error: err,
            }
        })

        return rtb
    }

    async createSession(data) : Promise<object> {
        const username = data.username;
        const password = data.password;

        if (password == null || password == undefined) {
            throw new HttpException("Missing password", 400);
        }

        const ue = await this.UserRepository.find({username: username})
        .then(res => {
            if (res.length == 0) {
                throw new HttpException("User not found", 400);
            }
            if (res[0].password != password) {
                throw new HttpException("Invalid Password", 400);
            }
            return {
                status: 0,
                data: res[0],
                error: null,
            };
        })
        .catch(err => {
            return {
                status: 1,
                data: null,
                error: err,
            }
        })

        if (ue.status == 1) {
            throw new HttpException(ue.error, 400);
        }

        const tokenExists = await this.SessionRepository.find({userId: ue.data.id})
        .then(res => {
            if (res.length != 0) {
                return {
                    statusCode: 200,
                    data: res[0].token,
                }
            } else {
                return {
                    statusCode: 204,
                    data: []
                }
            }
        })
        .catch(err => {
            return {
                statusCode: 400,
                data: null,
                error: err,
            }
        });

        if (tokenExists.statusCode == 200) {
            return {
                statusCode: 200,
                data: tokenExists.data
            }
        }

        let ses = this.SessionRepository.create();

        const tokenData = {
            id: ue.data.id,
            rank: ue.data.rank,
        }

        ses.token = jwt.sign(tokenData, "yolotron");
        ses.userId = ue.data.id;

        const rtb = this.SessionRepository.save(ses)
        .then(res => {
            return {
                statusCode: 200,
                data: res.token,
            };
        })
        .catch(err => {
            //throw new MongoException(err);
            Logger.log(err, "Fuck");
            return {
                statusCode: 420,
                error: err,
            }
        })

        return rtb;
    }

    async deleteSession(token) : Promise<object> {
        const se = await this.SessionRepository.find({token: token})
        .then(res => {
            if (res.length == 0) {
                return {
                    statusCode: 204,
                    msg: "Token doesn't exist in the db",
                    data: null
                }
            } else {
                return {
                    statusCode: 200,
                    data: res[0]
                }
            }
        })
        .catch(err => {
            // this cannot fail, fuck you
            return {
                statusCode: 420,
                msg: "Your shit is high, this can't happen",
                data: err
            }
        })

        if (se.statusCode == 204) {
            return se
        }

        this.SessionRepository.delete({token: token})
        se.statusCode = 202;
        se.msg = "Session deleted";
        return se;
    }
}
