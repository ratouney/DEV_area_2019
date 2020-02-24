import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from "typeorm";
import { IsDefined } from "class-validator";
import { Service } from ".";

@Entity()
export default class Action {
    @PrimaryGeneratedColumn('uuid')
    id: string;

    @Column({
        unique: true,
    })
    @IsDefined()
    name: string;

    @Column()
    description: string;

    @ManyToOne(type => Service, service => service.actions)
    service: Service;

    @Column({type: 'text', array: true, nullable: true })
    arguments: string[];
}