import { Controller, Get, Query, Post, Body } from '@nestjs/common';
import { AreaService } from './area.service';

@Controller('area')
export class AreaController {
    constructor(private readonly as: AreaService) {}

    @Get('me')
    getUsersAreas(@Query('token') token) : object {
        return this.as.getAreas(token);
    }

    @Post('new')
    createArea(@Query('token') token, @Body() params) : object {
        return this.as.createArea(token, params);
    }

    @Get('action')
    getActionsFromService(@Query('serviceId') serviceId) : object {
        return this.as.getActions(serviceId);
    }

    @Post('action/new')
    createAction(@Body() params) : object {
        return this.as.createAction(params);
    }

    @Get('reaction')
    getReactionFromService(@Query('serviceId') serviceId) : object {
        return this.as.getReactions(serviceId);
    }

    @Post('reaction/new')
    createReaction(@Body() params) : object {
        return this.as.createReaction(params);
    }

}
