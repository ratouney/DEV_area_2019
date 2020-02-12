import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import { SpotifyAPI } from "./api/spotify"

const Spotify = new SpotifyAPI();

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }
}
