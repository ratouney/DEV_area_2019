/*w
import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from "typeorm";
import { IsDefined } from "class-validator";
import { Service } from ".";

export enum TriggerType {
    action = "xd",
    reaction = "lol",
}


@Entity()
export default class Trigger {
    @PrimaryGeneratedColumn('uuid')
    id: string

    @Column()
    @IsDefined()
    name: string

    @Column({
        type: "enum",
        enum: TriggerType,
        default: TriggerType.action,
    })
    @IsDefined()
    type: TriggerType;

    @ManyToOne(type => Service, service => service.triggers)
    service: Service;
}
*/