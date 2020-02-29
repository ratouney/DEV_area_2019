import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { SpotifyAPI } from "./api/spotify"
import { Interval } from '@nestjs/schedule';
import { GmailAPI } from "./api/gmail"
import { GoogleSheetAPI } from "./api/gsheet"
import { PokeAPI } from "./api/pokemon"
import { NasaAPI } from "./api/nasa"
import { Meteo } from "./api/meteo"
import { ImgurAPI } from "./api/imgur"

const imgur = new ImgurAPI();
const pokapi = new PokeAPI();
const gmail = new GmailAPI();
const gsheet = new GoogleSheetAPI();
const Spotify = new SpotifyAPI();
const nasa = new NasaAPI();
const meteo = new Meteo();

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  async lol(info) {
	imgur.uploadPic("81c63147616456a2e23aafeea7aa5180115ac51e", await nasa.todayPicture(null, info));
  }

  @Get()
  getHello(): string {
	const info = {
		name : "Koetlin",
		senderMail : "timothee.cavalli.leaflighted@gmail.com",
		destMail : "maxime.de-la-fouchardiere@epitech.eu",
		url: "https://apod.nasa.gov/apod/image/2002/HotJupiter_ESACarreau_960.jpg",
		title : "lol2",
		text : "just another test",
		id : "7pvIXvM",
		data : 0,
		bool : false
	}
	imgur.userGotNewFav("81c63147616456a2e23aafeea7aa5180115ac51e", info)
//	this.lol(info)
//	meteo.weatherByCity(null, info)
//	meteo.uvLimitReached(null, info)
//	Spotify.setVolume("BQDb2xPg2iT6B_Hn29-hvXoS16lL2Zvb0TGhyxgheCBw4YOTRC1dIbsRFCf34hR_Nbpq2S8sKt4npnAZRFj2XAhAH_u5YG4yt1Vh6d-RCUqBDp59yWgRbjzFB8cE74pTrgicI0xy5nCZ7xaoE6CdsL6oU8Fq-ue1WEpXeqSR27Co9aV7BgtJpjo", info);

//	nasa.todayPicture(null, info)
//	nasa.marsPhoto(null, info)
//	pokapi.getPokemonByName(null, info)
//      gmail.hasGotNewMsg("ya29.a0Adw1xeXs5MS9mnT9fQnqZGoRoRaOmirRgfQzX1SPC35zbqmT2TA20br84e3UOy-p9x_LwRGDwWy0xWi8eF64yKhb5EMZ0oujKVJsT7GWee0fCbB1QYFoiZ1lqIBxGyHsJGHLvgUB0DfhmEzp_S54SzwXcFwv9ruBRLc", 123);
//      gsheet.createSheet("ya29.Il-_B_egrtEOJVeQgacPzl6jrKFh67moD32EkXr_km0r_pET2Ty0fd90_R-uhJlEkZDcVelB1lCFsbUo4eNM4Ou-mUyWry-Mfl1JM2iB1EVP_7L860CbkekAt9_YSZMuzg", info)
      gsheet.sheetChange("ya29.ImC_ByLpAK2zNCcrCjsTNoD7Qw8-P89bdUitDR_DrO13FzBCOzf4gbG5rGYQy8RMas-vrXLZZIUOi3ss_ERjC-cDxmXaiXRi_S6enYlQIsOwq9GLhW-nZGM9IO3lj8SC3kY", info);
    return this.appService.getHello();
  }
}
