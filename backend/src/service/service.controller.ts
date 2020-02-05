import { Controller, Get, Post, Body } from '@nestjs/common';
import { ServiceService } from './service.service'

@Controller('service')
export class ServiceController {
    constructor(private readonly ss: ServiceService) {}

    @Get()
    yolotron(): object {
        return this.ss.getAll();
    }

    @Post('new')
    hellothere(@Body() newServiceData) : object {
        return this.ss.createNew(newServiceData);
    }
}