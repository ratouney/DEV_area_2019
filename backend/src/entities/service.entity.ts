import { Entity, ObjectIdColumn, Column } from "typeorm";
import { ObjectID } from 'mongodb';
import { IsDefined } from "class-validator";

@Entity()
export default class Service {
    @ObjectIdColumn()
    id: ObjectID

    @Column({
        unique: true
    })
    @IsDefined()
    name: string;
}