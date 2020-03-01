import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, ManyToMany } from "typeorm";
import { IsDefined } from "class-validator";
import { User } from ".";
import Action from "./action.entity";
import Reaction from "./reaction.entity";

@Entity()
export default class Area {
    @PrimaryGeneratedColumn('uuid')
    id: string

    @Column({
        unique: true
    })
    @IsDefined()
    name: string

    @ManyToOne(type => User, user => user.areas)
    user: User

    @ManyToOne(type => Action, action => action.service)
    action: Action

    @ManyToOne(type => Reaction, reaction => reaction.service)
    reaction: Action

    @Column({
        nullable: true
    })
    timeCheck: number;

    @Column({
        nullable: true,
        default: "NULL",
    })
    lastRun: string;

    @Column({
        nullable: true,
        default: "NULL",
    })
    data: string;
}