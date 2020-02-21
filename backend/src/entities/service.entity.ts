import { Entity, Column, PrimaryGeneratedColumn, OneToMany, ManyToOne } from "typeorm";
import { IsDefined } from "class-validator";
import { Token } from ".";
import Action from "./action.entity";

@Entity()
export default class Service {
    @PrimaryGeneratedColumn('uuid')
    id: string

    @Column({
        unique: true
    })
    @IsDefined()
    name: string;

    @OneToMany(type => Token, token => token.service)
    tokens: Token[];

    @OneToMany(type => Action, action => action.service)
    actions: Action[];

    @OneToMany(type => Action, reaction => reaction.service)
    reactions: Action[];
}