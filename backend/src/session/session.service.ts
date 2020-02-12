import { Injectable, Logger, HttpException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Session, User } from '../entities';
import { Repository } from 'typeorm';
import jwt from "jsonwebtoken";

@Injectable()
export class SessionService {
    constructor(
        @InjectRepository(Session)
        private readonly SessionRepository : Repository<Session>,
        @InjectRepository(User)
        private readonly UserRepository : Repository<User>,
    ) {}

    getSession(id) : object {
        const rtb = this.SessionRepository.find({where: {user: id}})
        .then(res => {
            return {
                statusCode: 200,
                data: res,
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

    async createOrRetrieveSession(id, pw) : Promise<object> {
        let users = await this.UserRepository.find({where: {username: id}});

        Logger.log(users, "Found user");
        if (users.length == 0) {
            throw new HttpException("User not found", 400);
        }

        if (pw != users[0].password) {
            throw new HttpException("Invalid password", 403);
        }

        // User is authenticated
        let user = users[0];

        const tokens = await this.SessionRepository.find({where: {user: user}});

        // A login token already exists, return that one
        if (tokens.length > 0) {
            return {
                statusCode: 200,
                data: tokens[0].token
            }
        }

        const tokenData = {
            id: user.id,
            username: user.username,
            rank: user.rank
        }

        let entry = this.SessionRepository.create()

        entry.token = jwt.sign(tokenData, "yolotron");
        entry.user = user;

        const rtb = this.SessionRepository.save(entry)
        .then(res => {
            return {
                statusCode: 200,
                data: res.token,
            };
        })
        .catch(err => {
            Logger.log(err, "Fuck");
            return {
                statusCode: 420,
                error: err,
            }
        })        

        return rtb;
    }

    async invalidateToken(token) : Promise<object> {
        const rtb = await this.SessionRepository.delete({token : token});

        if (rtb.affected == 0) {
            return {
                statusCode: 400,
                error: "Token not found"
            }
        }
        return {
            statusCode: 205,
            msg: "User successfully logged out",
            data: null
        }
    }
}
