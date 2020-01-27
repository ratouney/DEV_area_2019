import { Controller, Get, Query, Post, Body, Delete } from '@nestjs/common';
import { SessionService } from './session.service';

@Controller('session')
export class SessionController {
    constructor(private readonly ss: SessionService) {}

    // Should be an admin command
    @Get()
    listSessions(@Query() query) : object {
        return this.ss.findSession(query);
    }

    // the login func
    @Post('login')
    createSession(@Body() body) : object {
        return this.ss.createSession(body);
    }

    @Delete('logout')
    anihilateSession(@Body('token') token) : object {
        return this.ss.deleteSession(token);
    }
}
