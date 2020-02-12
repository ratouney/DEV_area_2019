import { Controller, Get, Query, Body, Post, Delete } from '@nestjs/common';
import { SessionService } from './session.service';

@Controller('session')
export class SessionController {
    constructor(private readonly ss: SessionService) {}

    @Get()
    getCurrentSession(@Query('id') id) : object {
        return this.ss.getSession(id);
    }

    @Post('login')
    createSession(@Body('username') username, @Body('password') pw) : object {
        return this.ss.createOrRetrieveSession(username, pw);
    }

    @Delete('logout')
    deleteSession(@Body('token') token) : object {
        return this.ss.invalidateToken(token);
    }
}
