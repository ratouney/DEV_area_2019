import { ObjectIdColumn, Entity, Column, OneToMany } from 'typeorm';
import { ObjectID } from 'mongodb';
import { IsDefined, IsEmail } from 'class-validator';
import { Exclude } from 'class-transformer';
import { Session } from '.';
import Token from './token.entity';

export enum Rank {
    admin = "Chad",
    mod = "Gay",
    user = "Pleb",
}

@Entity()
export default class User {
    @ObjectIdColumn()
    id: ObjectID;

    @Column({
        unique: true
    })
    @IsDefined()
    username: string;

    @IsDefined()
    @Exclude({ toPlainOnly: true })
    @Column()
    password: string;

    @IsDefined()
    @IsEmail()
    @Column({
        unique: true
    })
    email: string;

    @Column({
        default: Rank.user
    })
    rank: Rank;

    @OneToMany(type => Session, session => session.user)
    sessions: Session[];

    @OneToMany(type => Token, token => token.user)
    tokens: Token[];
}