import { Controller, Get } from '@nestjs/common';
import { AppService } from './app.service';
import Spotify from "./api/Spotify"

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    Spotify.pauseSong("BQCvwYBNJ-SOZ8Vy3D3b63CE9i4uASgmDsfiXRISpouNMqYp4bR5sMOk-tlYAGK148Lmd3fI7zY0pPcesbYfyVuxAfyadL22-AkpO8X4OTTXXQ6mMJNNgYa0CPKTEba952SRxNOfIzhx-BAN5n-CeoLFNno-Gj3a7lcVmHA");
    return this.appService.getHello();
  }
}
