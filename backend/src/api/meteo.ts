import Request from "./requestClass"

const req = new Request();

const baseUrl = "https://api.openweathermap.org/data/"
const APIKey = "db9eb5e2ebd63b5affc7b6cb883c0610"

export class Meteo {

    constructor() {}

    /**
     * Async function which retrieve weather info for the city given
     * @param {string} cityName : City name of which we want informations
     */
    async weatherByCity(cityName : string) {
        const data = await req.getCall(baseUrl + "2.5/weather?q=" + cityName + "&APPID=" + APIKey)
        console.log(data);
        return data;
    }

    /**
     * Async function which retrieve weather forecast for the city given for
     * the next 5 days, every 3 hours
     * @param {string} cityName : City name of which we want informations
     */
    async weatherForecast(cityName : string) {
        const data = await req.getCall(baseUrl + "2.5/forecast?q=" + cityName + "&mode=json&APPID=" + APIKey)
	    return data;
    }

}
