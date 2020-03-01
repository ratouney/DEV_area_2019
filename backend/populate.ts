import { createConnection, Repository } from "typeorm"
import entities, { Reaction, Area, Action, Service } from './src/entities';

async function populateReactions(rr : Repository<Reaction>, sr : Repository<Service>) {
    const gmail = await sr.findOne({where: {name: "Gmail"}});

    let entry = await rr.findOne({where: {name: "gmail.sendMessage"}})
    if (entry == null) {
        const newMail = rr.create();
        newMail.name = "gmail.sendMessage";
        newMail.arguments = ["senderMail", "destMail", "title", "text"];
        newMail.service = gmail[0];
        newMail.description = "Send a mail to given person with given object and given body";
        await rr.save(newMail);
    }

    const spotify = await sr.findOne({where: {name: "Spotify"}});

    entry = await rr.findOne({where: {name: "spotify.nextSong"}})
    if (entry == null) {
        const nextSong = rr.create();
        nextSong.name = "spotify.nextSong";
        nextSong.arguments = [];
        nextSong.service = spotify;
        nextSong.description = "Play the next song in the queue";
        await rr.save(nextSong);
    }
}

async function populateActions(ar : Repository<Action>, sr : Repository<Service>) {
    const imgur = await sr.findOne({name: "Imgur"});
    
    let entry = await ar.findOne({where: {name: "imgur.isThereNewPicForTag"}});
    if (entry == null) {
        const newPic = ar.create();
        newPic.name = "imgur.isThereNewPicForTag";
        newPic.arguments = ["name"];
        newPic.service = imgur;
        newPic.description = "A new picture has been added with defined tag";
        await ar.save(newPic);
    }
    
    entry = await ar.findOne({where: {name: "imgur.userGotNewFav"}});
    if (entry == null) {
        const getNewFav = ar.create();
        getNewFav.name = "imgur.userGotNewFav"
        getNewFav.arguments = ["name"];
        getNewFav.service = imgur;
        getNewFav.description = "User defined has favorite a new picture";
        await ar.save(getNewFav);
    }
    
    entry = await ar.findOne({where: {name: "imgur.getNewVote"}});
    if (entry == null) {
        const getNewVote = ar.create();
        getNewVote.name = "imgur.getNewVote"
        getNewVote.arguments = ["id"];
        getNewVote.service = imgur;
        getNewVote.description = "A post given got new votes";
        await ar.save(getNewVote);
    }

    const nasa = await sr.findOne({name: "Nasa"});

    entry = await ar.findOne({where: {name: "nasa.todayPicture"}})
    if (entry == null) {
        const todayPic = ar.create();
        todayPic.name = "nasa.todayPicture";
        todayPic.arguments = [];
        todayPic.service = nasa;
        todayPic.description = "Get picture of the day posted by NASA";
        await ar.save(todayPic);
    }
}

async function populateServices(repo : Repository<Service>) {
    const serv = await repo.find();
    console.log("Current services : ", serv);

    let entry = await repo.findOne({where: {name: "Gmail"}})
    if (entry == null) {
        const gmail = await repo.create();
        gmail.name = "Gmail"
        repo.save(gmail);
    }
    
    entry = await repo.findOne({where: {name: "Sheet"}})
    if (entry == null) {
        const sheet = await repo.create();
        sheet.name = "Sheet"
        repo.save(sheet);
    }
    
    entry = await repo.findOne({where: {name: "Pokemon"}})
    if (entry == null) {
        const pokemon = await repo.create();
        pokemon.name = "Pokemon"
        repo.save(pokemon);
    }
    
    entry = await repo.findOne({where: {name: "Nasa"}})
    if (entry == null) {
        const nasa = await repo.create();
        nasa.name = "Nasa"
        repo.save(nasa);
    }
    
    entry = await repo.findOne({where: {name: "Spotify"}})
    if (entry == null) {
        const spotify = await repo.create();
        spotify.name = "Spotify"
        repo.save(spotify);
    }
    
    entry = await repo.findOne({where: {name: "Weather"}})
    if (entry == null) {
        const weather = await repo.create();
        weather.name = "Weather"
        repo.save(weather);
    }
    
    entry = await repo.findOne({where: {name: "Imgur"}})
    if (entry == null) {
        const imgur = await repo.create();
        imgur.name = "Imgur"
        repo.save(imgur);
    }
    
}

async function populate() {
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
    
    console.log("Populating Database...");
    
    const serviceRepo = _conn.getRepository(Service);    
    await populateServices(serviceRepo);
    console.log("Added Services");
    
    const actionRepo = _conn.getRepository(Action);
    await populateActions(actionRepo, serviceRepo);
    console.log("Added Actions");

    const reactionRepo = _conn.getRepository(Reaction);
    await populateReactions(reactionRepo, serviceRepo);
    console.log("Added Reactions");

    // console.log("MERDE")
    
    _conn.close();
}

export default populate;