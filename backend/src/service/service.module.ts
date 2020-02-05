import { Module } from '@nestjs/common';
import { ServiceService } from './service.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Service } from '../entities';
import { ServiceController } from './service.controller';

@Module({
  imports: [TypeOrmModule.forFeature([Service])],
  providers: [ServiceService],
  controllers: [ServiceController]
})
export class ServiceModule {}
