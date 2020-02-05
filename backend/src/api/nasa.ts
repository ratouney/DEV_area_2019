import Request from "./requestClass"
import key from "./key"

const req = new Request();
const baseUrl = "https://api.nasa.gov/";

export class NasaAPI {

    async todayPicture() {
        const data = await req.getCall(baseUrl + "planetary/apod?api_key=" + key.nasa);
        console.log(data)
        return data;
    }

    async marsWeather() {
        const data = await req.getCall(baseUrl + "insight_weather/?api_key=" + key.nasa + "&feedtype=json&ver=1.0");
        return data;
    }
}
