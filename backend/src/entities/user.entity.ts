import { ObjectIdColumn, Entity, Column } from 'typeorm';
import { ObjectID } from 'mongodb';
import { IsDefined, IsEmail } from 'class-validator';

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
}