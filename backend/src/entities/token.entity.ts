import { Entity, ObjectIdColumn, Column, ManyToOne } from "typeorm";
import { ObjectID } from 'mongodb';
import { IsDefined } from "class-validator";

@Entity()
export default class Token {
    @ObjectIdColumn()
    id: ObjectID

    @Column()
    @IsDefined()
    data: string;

    @Column()
    @IsDefined()
    userId: string;

    @Column()
    @IsDefined()
    serviceId: string;
}