import Request from "./requestClass"
import key from "./key"

const req = new Request();

const baseUrl = "https://api.openweathermap.org/data/"
// const APIKey = "db9eb5e2ebd63b5affc7b6cb883c0610"

export class Meteo {

    constructor() {}

    /**
     * Async function which retrieve weather info for the city given
     * @param {string} cityName : City name of which we want informations
     */
    async weatherByCity(cityName : string) {
        const data = await req.getCall(baseUrl + "2.5/weather?q=" + cityName + "&APPID=" + key.weather)
        console.log(data);
        return data;
    }

    async uvLimitReached(cityName, limit) {
        const uv = await req.getCall("https://api.opencagedata.com/geocode/v1/json?q=PLACENAME&key=" + key.uv)
        console.log(uv);
        const lat = uv.results[0].geometry.lat;
        const long = uv.results[0].geometry.long;
        const data = await req.getCall(baseUrl + "2.5/uvi?appid=" + key.weather + "&lat=" + lat + "&lon=" + long)
        return data.value > limit
    }
}
