import { Controller, Get, Query, Body, Post } from '@nestjs/common';
import { TokenService } from './token.service';
import { Token } from '../entities';

@Controller('token')
export class TokenController {
    constructor(private readonly ts: TokenService) {}

    @Get()
    getTokenForUser(@Query('token') token) : object {
        return this.ts.getTokensFromUser(token);
    }

    @Post('new')
    createNEwToken(@Query('token') authToken, @Body() params) : object {
        return this.ts.createToken(authToken, params);
    }
}
