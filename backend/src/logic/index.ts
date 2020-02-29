import apis from './../api';
import { createConnection } from 'typeorm';
import entities, { Area, Action } from './../entities';

async function runArea(id) {
    var _conn = await createConnection({
        "type": "postgres",
        "host": "localhost",
        "port": 5432,
        "username": "postgres",
        "password": "postgres",
        "database": "area",
        "entities": entities,
        "name": `runArea-${id}`,
        "logging": true
    })

    const areaRepo = _conn.getRepository(Area);
    
    const entry = await areaRepo.findOne(id, {relations: ["action", "action.service", "reaction", "reaction.service"]});

    console.log("Running area : ", entry);

    const actionParts = entry.action.name.split('.');
    const actionBlock = apis[actionParts[0]];
    if (actionBlock == undefined || actionBlock == null) {
        console.log("ALERTE ROUGE DE MERDE YA PAS DE MODULE : ", actionParts[0]);
        return false;
    }
    const actionfunc = actionBlock[actionParts[1]];
    if (actionfunc == undefined || actionfunc == null) {
        console.log("ALERTE ROUGE DE MERDE YA PAS CETTE FONCTION DANS LE MODULE : ", actionParts[1]);
        return false;
    }

    const rt = await actionfunc();

    const reactionParts = entry.reaction.name.split('.');
    const reactionBlock = apis[reactionParts[0]];
    if (reactionBlock == undefined || reactionBlock == null) {
        console.log("ALERTE ROUGE DE MERDE YA PAS DE MODULE : ", reactionParts[0]);
        return false;
    }
    const reactionfunc = reactionBlock[reactionParts[1]];
    if (reactionfunc == undefined || reactionfunc == null) {
        console.log("ALERTE ROUGE DE MERDE YA PAS CETTE FONCTION DANS LE MODULE : ", reactionParts[1]);
        return false;
    }

    const rtb = await reactionfunc(rt);

    _conn.close();
}

export default runArea;
