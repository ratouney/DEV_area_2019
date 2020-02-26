import Request from "./requestClass"
import key from "./key"

const req = new Request();

const baseUrl = "https://api.openweathermap.org/data/"

export class Meteo {

    constructor() {}

    async weatherByCity(accesToken , info) {
        const data = await req.getCall(baseUrl + "2.5/weather?q=" + info.name.toLowerCase() + "&APPID=" + key.weather)
	const toReturn = {
		title : info.name + " Meteo",
		name : info.name,
		text : "Today meteo for " + info.name + " is :" + data.weather[0].description,
		data : data.weather[0].description,
		bool : true
	}
        return toReturn;
    }

    async uvLimitReached(accessToken, info) {
        const uv = await req.getCall("https://api.opencagedata.com/geocode/v1/json?q=" + info.name + "&key=" + key.uv)
        const lat = uv.results[0].geometry.lat;
        const long = uv.results[0].geometry.lng;
        const data = await req.getCall(baseUrl + "2.5/uvi?appid=" + key.weather + "&lat=" + lat + "&lon=" + long)
	const toReturn = {
		title : "UV limit reached",
		text : "The UV limit of " + info.data + " set for " + info.name + " has been reached",
		data : info.data,
		name : info.name,
		bool : data.value >= info.data
	}
        return toReturn
    }
}
