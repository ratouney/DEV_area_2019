import { createConnection } from "typeorm"
import entities, { Reaction, Area, Service } from './entities';

async function quickdbmodif() {
    var _conn = await createConnection({
        "type": "postgres",
        "host": process.env.DB_HOST,
        "port": 5432,
        "username": process.env.DB_USER,
        "password": process.env.DB_PASS,
        "database": process.env.DB_NAME,
        "entities": entities,
        "name": "quickupdate",
        "logging": true
    })

    const reactionRepo = _conn.getRepository(Reaction);

    const idToUpdate = "0132b71e-c69f-4c23-a4b0-04fdf2b9d5fd";

    const entry = await reactionRepo.findOne(idToUpdate);

    console.log("Found and updating : ", entry);

    const serviceRepo = _conn.getRepository(Service);
    const serv = await serviceRepo.findOne({where: {name: "Gmail"}});

    entry.service = serv

    console.log("Entry should be now : ", entry);

    const rt = await reactionRepo.update(idToUpdate, entry);

    console.log("RT : ", rt);

    _conn.close();
}

export default quickdbmodif;