import { Controller, Get, Query, Post, Body, Put } from '@nestjs/common';
import { AreaService } from './area.service';
import { Cron, Interval } from '@nestjs/schedule';

@Controller('area')
export class AreaController {
    constructor(private readonly as: AreaService) {}

    @Interval(30000)
    checkForRunningAreas() {
        console.log("Now i'm going to check if i need to run any areas");
    }

    @Get('me')
    getUsersAreas(@Query('token') token) : object {
        return this.as.getAreas(token);
    }   

    @Post('new')
    createArea(@Query('token') token, @Body() params) : object {
	console.log("AreaControllerBody :", params);
        return this.as.createArea(token, params);
    }

    @Get('trigger')
    triggerArea(@Query('id') id) : object {
        return this.as.updateAreaRun(id);
    }

    @Get('shouldTrigger')
    shouldTriggerArea(@Query('id') id) : object {
        return this.as.checkAreaShouldRun(id);
    }

    @Get('action')
    getActionsFromService(@Query('serviceId') serviceId) : object {
        return this.as.getActions(serviceId);
    }

    @Post('action/new')
    createAction(@Body() params) : object {
        return this.as.createAction(params);
    }

    @Put('action/update')
    updateAction(@Query('id') id, @Body() params) : object {
        return this.as.updateAction(id, params);
    }

    @Get('reaction')
    getReactionFromService(@Query('serviceId') serviceId) : object {
        return this.as.getReactions(serviceId);
    }

    @Post('reaction/new')
    createReaction(@Body() params) : object {
        return this.as.createReaction(params);
    }

    @Put('reaction/update')
    updateReaction(@Query('id') id, @Body() params) : object {
        return this.as.updateReaction(id, params);
    }

}
