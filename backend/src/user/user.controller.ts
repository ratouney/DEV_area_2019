import { Controller, Get, Query, Post, Body, Put, Delete } from '@nestjs/common';
import { UserService } from './user.service';

@Controller('user')
export class UserController {
    constructor(private readonly us: UserService) {}

    @Get()
    uselessfunctionname(@Query() query): object {
        return this.us.findUser(query);
    }

    @Post('new')
    didyouknowfunctionnamesdontmatter(@Body() newUserData): object {
        return this.us.createUser(newUserData);
    }

    @Put('update')
    yepanotheroneofthese(@Query('id') id : string, @Body() data : object) : object {
        return this.us.updateUser(id, data);
    }

    @Delete('delete')
    helothere(@Query('id') id : string) : object {
        return {status: "no"};
    }
}
