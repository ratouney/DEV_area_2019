import { Entity, ObjectIdColumn, Column, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { ObjectID } from 'mongodb';
import { IsDefined } from "class-validator";
import { User } from ".";
import Service from "./service.entity";

@Entity()
export default class Token {
    @PrimaryGeneratedColumn('uuid')
    id: string

    @Column()
    @IsDefined()
    token: string;

    @ManyToOne(type => User, user => user.tokens)
    user: User;

    @ManyToOne(type => Service, service => service.tokens)
    service: Service;
}