import { Entity, ObjectIdColumn, Column, ManyToOne } from "typeorm";
import { ObjectID } from 'mongodb';
import { User } from ".";

@Entity()
export default class Session {
    @ObjectIdColumn()
    id: ObjectID;

    @ManyToOne(type => User, user => user.sessions)
    user: User;

    @Column()
    userId: string;
    
    @Column()
    token: string;
}