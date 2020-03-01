import Request from "./requestClass"
import key from "./key"

const req = new Request();
const baseUrl = "https://api.nasa.gov/";

export class NasaAPI {
	
	async todayPicture(accessToken, info) {
		const data = await req.getCall(baseUrl + "planetary/apod?api_key=" + key.nasa);
		const toReturn = {
			url : data.url,
			title : data.title,
			text : "Photo date : " + data.date + "\nExplanation : " + data.explanation
		}
		return toReturn;
	}
	
	async marsPhoto(accessToken, info) {
		const data = await req.getCall(baseUrl + "mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + key.nasa);
		const toReturn = {
			url : data.photos[info.id].img_src,
			title : "Mars photo n°" + info.id,
			text : "Mars photo taken by rover " + data.photos[info.id].rover.name + "\nPhoto url : " + data.photos[info.id].img_src,
			id : info.id > 855 ? 0 : info.id + 1
		}
		return toReturn
	}
}
