import { Entity, ObjectIdColumn, Column, ManyToOne, PrimaryGeneratedColumn, OneToOne, JoinColumn } from "typeorm";
import { Exclude } from 'class-transformer';
import { User } from ".";
import { IsDefined } from "class-validator";

@Entity()
export default class Session {
    @PrimaryGeneratedColumn('uuid')
    id: string;
    
    @OneToOne(type => User)
    @JoinColumn()
    user: User;

    @Column()
    @IsDefined()
    token: string;
}