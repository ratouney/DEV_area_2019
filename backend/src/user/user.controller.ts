import { Controller, Get, Query, Post, Body, Put } from '@nestjs/common';
import { UserService } from './user.service';

@Controller('user')
export class UserController {
    constructor(private readonly us: UserService) {}

    @Get('all')
    getAllUsers() : object {
        return this.us.getAllUsers();
    }

    @Get()
    getSpecificUser(@Query('id') id) : object {
        return this.us.getUser(id);
    }

    @Post('new')
    createNewUser(@Body() body) : object {
        return this.us.createUser(body);
    }

    @Put('update')
    updateExistingUser(@Query('id') id, @Body() params) : object {
        return this.us.updateUser(id, params);
    }
}
