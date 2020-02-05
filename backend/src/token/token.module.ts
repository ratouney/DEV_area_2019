import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Token, User, Service } from '../entities';
import { TokenService } from './token.service';
import { TokenController } from './token.controller';

@Module({
    imports: [TypeOrmModule.forFeature([Token, User, Service])],
    providers: [TokenService],
    controllers: [TokenController]
})
export class TokenModule {}
