import { createConnection } from "typeorm"
import entities, { Reaction, Area } from './entities';

async function quickdbmodif() {
    var _conn = await createConnection({
        "type": "postgres",
        "host": "localhost",
        "port": 5432,
        "username": "postgres",
        "password": "postgres",
        "database": "area",
        "entities": entities,
        "name": "quickupdate",
        "logging": true
    })

    const reactionRepo = _conn.getRepository(Area);

    const entry = await reactionRepo.findOne("a1c7f2e7-53ae-4136-b5f8-f8a7b0ac9ee3");

    console.log("Found and updating : ", entry);

    const data = {
        senderMail: "ratouney1998@gmail.com",
        destMail: "maxime.de-la-fouchardiere@epitech.eu",
        title: "Hello bitch",
    }

    entry.data = JSON.stringify(data);

    console.log("Entry should be now : ", entry);

    const rt = await reactionRepo.update("a1c7f2e7-53ae-4136-b5f8-f8a7b0ac9ee3", entry);

    console.log("RT : ", rt);

    _conn.close();
}

export default quickdbmodif;