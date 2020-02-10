import { Entity, ObjectIdColumn, Column, ManyToOne } from "typeorm";
import { ObjectID } from 'mongodb';
import { User } from ".";
import { IsDefined } from "class-validator";

@Entity()
export default class Session {
    @ObjectIdColumn()
    id: ObjectID;

    @Column()
    @IsDefined()
    userId: string;
    
    @Column()
    @IsDefined()
    token: string;
}