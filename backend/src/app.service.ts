import { Injectable } from '@nestjs/common';

@Injectable()
export class AppService {
  getHello(): string {
    return 'Welcome to the fucking shitshow, elso known as our shitty AREA backend. Get the postman collection that explains all this garbage here : https://app.getpostman.com/join-team?invite_code=4ae64c521a2a18008d1cf4d2888fc39f&ws=7942a905-93b7-43e2-bc7c-740afd8bdbc8';
  }
}
