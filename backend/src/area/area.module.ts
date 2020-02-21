import { Module } from '@nestjs/common';
import { AreaService } from './area.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Area, Action, Reaction, Session, Service, User } from '../entities';
import { AreaController } from './area.controller';

@Module({
  imports: [TypeOrmModule.forFeature([Area, Action, Reaction, Session, Service, User])],
  providers: [AreaService],
  controllers: [AreaController]

})
export class AreaModule {}
