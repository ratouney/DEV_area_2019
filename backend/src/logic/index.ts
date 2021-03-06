import apis from './../api';
import { createConnection } from 'typeorm';
import entities, { Area, Action, Token } from './../entities';

async function runArea(id) {
    var _conn = await createConnection({
        "type": "postgres",
        "host": process.env.DB_HOST,
        "port": 5432,
        "username": process.env.DB_USER,
        "password": process.env.DB_PASS,
        "database": process.env.DB_NAME,
        "entities": entities,
        "name": `runArea-${Date().toString()}`,
        "logging": true
    })

    const areaRepo = _conn.getRepository(Area);
    const tokenRepo = _conn.getRepository(Token);
    
    // Getting the area data from the DB
    const entry = await areaRepo.findOne(id, {relations: ["action", "action.service", "reaction", "reaction.service", "user"]});

    console.log("Running area : ", entry);

    // Figuring out which action to run
    const actionParts = entry.action.name.split('.');
    console.log("ActionParts : ", actionParts);
    const actionBlock = apis[actionParts[0]];
    if (actionBlock == undefined || actionBlock == null) {
        console.log("ALERTE ROUGE DE MERDE YA PAS DE MODULE : ", actionParts[0]);
        return false;
    }

    // Matching the function
    const actionfunc = actionBlock[actionParts[1]];
    if (actionfunc == undefined || actionfunc == null) {
        console.log("ALERTE ROUGE DE MERDE YA PAS CETTE FONCTION DANS LE MODULE : ", actionParts[1]);
        return false;
    }

    // Getting the user's access token for that service
    const actionToken = await tokenRepo.findOne({where: {user: entry.user.id, service: entry.action.id}});
    console.log("Action Token : ", actionToken);

    let rt = await actionfunc(actionToken == null ? "" : actionToken.token);

    console.log("AFTER ACTION : ", rt);
    console.log("Apparently there is a parse error here : [", entry.data);
    console.log("SEcond char : ", entry.data[1]);
    const data = JSON.parse(entry.data);
    console.log("Adding AREA.data to the block : ", data);
    rt = {
        ...data,
        ...rt
    };

    // Doing the same shit for reaction
    const reactionParts = entry.reaction.name.split('.');
    console.log("ReactionParts : ", reactionParts);
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

    const reactionTokens = await tokenRepo.find({where: {service: entry.reaction.service, user: {id: entry.user.id}}, relations: ["service", "user"]});
    const reactionToken = reactionTokens.length > 0 ? reactionTokens[reactionTokens.length - 1].token : "";

    // another one

    console.log("Sending to Reaction : ", rt);
    console.log("With token : ", reactionToken);
    const rtb = await reactionfunc(reactionToken, rt);

    // QUpd
    const val = Date.now().toString();
    await areaRepo.update(id, {lastRun: val,});

    _conn.close();
}

export default runArea;
