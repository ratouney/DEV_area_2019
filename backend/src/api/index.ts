import { GmailAPI } from './gmail';
import { GoogleSheetAPI } from './gsheet';
import { ImgurAPI } from './imgur';
import { Meteo } from './meteo';
import { NasaAPI } from './nasa';
import { PokeAPI } from './pokemon';
import { SpotifyAPI } from './spotify';

const gmail = new GmailAPI();
const sheet = new GoogleSheetAPI();
const imgur = new ImgurAPI();
const meteo = new Meteo();
const nasa = new NasaAPI();
const pokemon = new PokeAPI();
const spotify = new SpotifyAPI();

export default {
    sheet,
    spotify,
    imgur,
    gmail,
    meteo,
    pokemon,
    nasa,
};