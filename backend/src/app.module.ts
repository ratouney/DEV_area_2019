import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserModule } from './user/user.module';
import { SessionModule } from './session/session.module';
import { TokenModule } from './token/token.module';
import { ServiceModule } from './service/service.module';
import Entities from './entities';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: "mongodb",
      useUnifiedTopology: true,
      url: "mongodb+srv://username:paspassword@areacluster-dguyh.mongodb.net/dev?retryWrites=true&w=majority",
      synchronize: true,
      logging: true,
      entities: Entities,
    }),
    UserModule,
    SessionModule,
    TokenModule,
    ServiceModule
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}