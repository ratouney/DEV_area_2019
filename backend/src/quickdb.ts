import { createConnection } from "typeorm"
import entities, { Reaction } from './entities';

async function quickdbmodif() {
    return null;
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

    const reactionRepo = _conn.getRepository(Reaction);

    const entry = await reactionRepo.findOne("666d2309-b434-47bf-abcd-b5d6346261d1");

    entry.arguments = ["receiver"]

    const rt = await reactionRepo.update("666d2309-b434-47bf-abcd-b5d6346261d1", entry);

    console.log("RT : ", rt);

    _conn.close();
}

export default quickdbmodif;