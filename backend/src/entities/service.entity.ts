import { Entity, Column, PrimaryGeneratedColumn, OneToMany } from "typeorm";
import { IsDefined } from "class-validator";
import { Token } from ".";

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
}