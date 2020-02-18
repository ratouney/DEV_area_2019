import { Entity, Column, OneToMany, PrimaryGeneratedColumn, OneToOne, JoinColumn } from 'typeorm';
import { IsDefined, IsEmail } from 'class-validator';
import { Exclude } from 'class-transformer';
import { Session } from '.';
import Token from './token.entity';
import Area from './area.entity';

export enum Rank {
    admin = "Chad",
    mod = "Gay",
    user = "Pleb",
}

@Entity()
export default class User {
    @PrimaryGeneratedColumn('uuid')
    id: string;

    @Column({
        unique: true
    })
    @IsDefined()
    username: string;

    @Exclude({ toPlainOnly: true })
    @Column()
    @IsDefined()
    password: string;

    @Column({
        unique: true,
    })
    @IsEmail()
    @IsDefined()
    email: string;

    @Column({
        default: Rank.user
    })
    rank: Rank;

    @OneToMany(type => Token, token => token.user)
    tokens: Token[];

    @OneToMany(type => Area, area => area.user)
    areas: Area[];

    @Column({
        default: 0,
    })
    pokemon: Number;

    @Column({
        default: 0,
    })
    marsphoto: Number;
}