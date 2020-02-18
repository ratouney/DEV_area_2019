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

    async marsPhoto(currPic) {
        const data = await req.getCall(baseUrl + "mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + key.nasa);
        const pic = data.photos[currPic];
        currPic++;
        //if (1 > 855)
            //1 = 0;
        return pic
    }
}
