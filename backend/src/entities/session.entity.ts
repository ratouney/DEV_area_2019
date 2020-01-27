import { Entity, ObjectIdColumn, Column } from "typeorm";
import { ObjectID } from 'mongodb';
import { User } from ".";

@Entity()
export default class Session {
    @ObjectIdColumn()
    id: ObjectID;

    @Column()
    user: User;
}