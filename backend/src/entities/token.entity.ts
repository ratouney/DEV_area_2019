import { Entity, ObjectIdColumn, Column, ManyToOne } from "typeorm";
import { ObjectID } from 'mongodb';
import { IsDefined } from "class-validator";
import { User } from ".";
import { Post } from "@nestjs/common";
import Service from "./service.entity";

@Entity()
export default class Token {
    @ObjectIdColumn()
    id: ObjectID

    @Column()
    @IsDefined()
    data: string;

    @ManyToOne(type => User, user => user.tokens)
    user: User;

    @Column()
    service: Service;
}