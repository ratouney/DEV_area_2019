import { Module } from '@nestjs/common';
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
import Entities  from './entities';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      "type": "postgres",
      "host": "localhost",
      "port": 5432,
      "username": "root",
      "password": "root",
      "database": "area",
      "entities": Entities,
      "synchronize": true,
      "logging": true
    }),
    UserModule,
    SessionModule,
    ServiceModule,
    TokenModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}