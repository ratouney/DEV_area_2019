import { Module } from '@nestjs/common';
import { TokenService } from './token.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Token, Service, User, Session } from '../entities';
import { TokenController } from './token.controller';

@Module({
  imports: [TypeOrmModule.forFeature([Token, Service, User, Session])],
  providers: [TokenService],
  controllers: [TokenController],
})
export class TokenModule {}
