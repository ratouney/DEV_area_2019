import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { SpotifyAPI } from "./api/spotify"
import { Interval } from '@nestjs/schedule';
import { GmailAPI } from "./api/gmail"
import { GoogleSheetAPI } from "./api/gsheet"

const gmail = new GmailAPI();
const gsheet = new GoogleSheetAPI();
const Spotify = new SpotifyAPI();

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
      gmail.hasGotNewMsg("ya29.Il-_B55w44krRLPXDYWhSgXYAI5SI9LxH7mZIIYAAiFd6AZvii4KrAKbNKFnckbDql_Qa_UN-PmOThRvq0-9ERyg7y1y8MamtpU5zo-k7BkIwPbabYEf99ucGz0AOrEsEw", 123);
      gmail.createDraft("ya29.Il-_B55w44krRLPXDYWhSgXYAI5SI9LxH7mZIIYAAiFd6AZvii4KrAKbNKFnckbDql_Qa_UN-PmOThRvq0-9ERyg7y1y8MamtpU5zo-k7BkIwPbabYEf99ucGz0AOrEsEw", "trash.dev.mail@gmail.com", "mpm", "l", "zarrz");
      gsheet.sheetChange("ya29.Il-_B55w44krRLPXDYWhSgXYAI5SI9LxH7mZIIYAAiFd6AZvii4KrAKbNKFnckbDql_Qa_UN-PmOThRvq0-9ERyg7y1y8MamtpU5zo-k7BkIwPbabYEf99ucGz0AOrEsEw", "1Pu_cKkzCZVBHRjqtadL4-W1lxTRFhHPHQ235z8dtX84", 12345678)
    return this.appService.getHello();
  }
}
