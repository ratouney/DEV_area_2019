import { Controller, Query, Get, Post, Body } from '@nestjs/common';
import { TokenService } from './token.service';

@Controller('token')
export class TokenController {
    constructor(private readonly ts: TokenService) {}

    @Get()
    generalkenobi(@Query() q): object {
        return this.ts.listTokens(q);
    }

    @Post('new')
    asurprisetobesure(@Body() newTokenData): object {
        return this.ts.createToken(newTokenData);
    }
}
