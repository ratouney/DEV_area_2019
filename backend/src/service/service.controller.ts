import { Controller, Get, Post, Body } from '@nestjs/common';
import { ServiceService } from './service.service';

@Controller('service')
export class ServiceController {
    constructor(private readonly ss: ServiceService) {}

    @Get()
    getAllServices() : object {
        return this.ss.getAllServices();
    }

    @Post('new')
    createService(@Body() params) : object {
        return this.ss.createNewService(params);
    }
}
