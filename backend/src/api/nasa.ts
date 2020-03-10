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

		const infoid = Math.round(Math.random() * data.photos.length);
		const toReturn = {
			url : data.photos[infoid].img_src,
			title : "Mars photo n°" + infoid,
			text : "Mars photo taken by rover " + data.photos[infoid].rover.name + "\nPhoto url : " + data.photos[infoid].img_src,
			id : infoid > 855 ? 0 : infoid + 1
		}
		return toReturn
	}
}
