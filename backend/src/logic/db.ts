import { createConnection } from 'typeorm';
import entities from './../entities';

const dbc = createConnection({
      "type": "postgres",
      "host": "localhost",
      "port": 5432,
      "username": "postgres",
      "password": "postgres",
      "database": "area",
      "entities": entities,
      "logging": true
})

export default dbc;