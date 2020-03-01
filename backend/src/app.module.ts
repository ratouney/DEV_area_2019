import { Module } from '@nestjs/common';
import { ScheduleModule } from '@nestjs/schedule';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserController } from './user/user.controller';
import { UserModule } from './user/user.module';
import { SessionController } from './session/session.controller';
import { SessionModule } from './session/session.module';
import { ServiceController } from './service/service.controller';
import { ServiceModule } from './service/service.module';
import { TokenController } from './token/token.controller';
import { TokenModule } from './token/token.module';
import { AreaController } from './area/area.controller';
import { AreaModule } from './area/area.module';
import Entities  from './entities';

require('dotenv').config()

console.log("APP.MODULE : ", process.env.DB_USER);

@Module({
  imports: [
    TypeOrmModule.forRoot({
      "type": "postgres",
      "host": process.env.DB_HOST,
      "port": 5432,
      "username": process.env.DB_USER,
      "password": process.env.DB_PASS,
      "database": process.env.DB_NAME,
      "entities": Entities,
      "synchronize": true,
      "logging": true
    }),
    ScheduleModule.forRoot(),
    UserModule,
    SessionModule,
    ServiceModule,
    TokenModule,
    AreaModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}