import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { Repository, createConnection } from 'typeorm';
import entities, { Area } from './entities';
import { InjectRepository } from '@nestjs/typeorm';
import apis from './api';
import runArea from './logic';
import quickdbmodif from './quickdb';
import populate from '../populate';

require('dotenv').config()

async function initConnection() {
  console.log("Connecting : ", process.env.DB_USER);
  var _conn = await createConnection({
    "type": "postgres",
    "host": process.env.DB_HOST,
    "port": 5432,
    "username": process.env.DB_USER,
    "password": process.env.DB_PASS,
    "database": process.env.DB_NAME,
    "entities": entities,
    "name": "fuck off",
    "logging": true
  })
  
  const areaRepo = _conn.getRepository(Area);
  
  const entries = await areaRepo.find();
  
  entries.forEach(elem => {
    let dt = new Date();
    if (elem.lastRun != "NULL")
      dt = new Date(parseInt(elem.lastRun));
    const n = new Date();
    
    const diff = Math.abs((n.getHours() * 60) + n.getMinutes() - (dt.getHours() * 60) - dt.getMinutes());
    
    console.log("Last ran : ", dt);
    console.log("Now      : ", n);
    console.log("Checked time diff: ", diff);
    if (diff > elem.timeCheck || elem.lastRun == "NULL") {
      console.log(`Run the area ${elem.id}`);
      runArea(elem.id);
    }
  })

  console.log("Areas checked !");
  _conn.close();
}

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  await app.listen(process.env.PORT || 3001);
}


console.log("ENVIRONMENT : ", process.env.HOST);
//populate();
//setInterval(initConnection, 10000);
//quickdbmodif();
initConnection();
bootstrap();
