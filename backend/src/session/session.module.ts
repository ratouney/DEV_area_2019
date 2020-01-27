import { Module } from '@nestjs/common';
import { SessionService } from './session.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User, Session } from '../entities';
import { SessionController } from './session.controller';

@Module({
  imports: [TypeOrmModule.forFeature([User, Session])],
  providers: [SessionService],
  controllers: [SessionController]
})
export class SessionModule {}
