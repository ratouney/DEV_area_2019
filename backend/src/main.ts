import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Repository, createConnection } from 'typeorm';
import entities, { Area } from './entities';
import { InjectRepository } from '@nestjs/typeorm';
import apis from './api';
import runArea from './logic';
import quickdbmodif from './quickdb';

async function initConnection() {
  var _conn = await createConnection({
    "type": "postgres",
    "host": "localhost",
    "port": 5432,
    "username": "postgres",
    "password": "postgres",
    "database": "area",
    "entities": entities,
    "name": "fuck off",
    "logging": true
  })
  
  const areaRepo = _conn.getRepository(Area);
  
  const entries = await areaRepo.find();
  
  entries.forEach(elem => {
    const dt = new Date(parseInt(elem.lastRun));
    const n = new Date();
    
    const diff = n.getMinutes() + (n.getHours() * 60) - (dt.getHours() * 60) - dt.getMinutes();
    
    console.log("Checked time diff: ", diff);
    if (diff > elem.timeCheck) {
      console.log(`Run the area ${elem.id}`);
      runArea(elem.id);
    }
  })

  console.log("Areas checked !");
  _conn.close();
}

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(3001);
}

setInterval(initConnection, 10000);
quickdbmodif();
bootstrap();