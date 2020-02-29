import Request from "./requestClass"
import key from "./key"

const req = new Request();

export class GoogleSheetAPI {

    async createSheet(accessToken, info) {
        const head = 'Bearer ' + accessToken
        const uri = "https://sheets.googleapis.com/v4/spreadsheets?key=" + key.google.APIKey
        const config = {
            url: uri,
            method: 'post',
            headers: {
                'Authorization' : head,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {
		"properties": {
			"title": info.title ? info.title : info.name ? info.name : "New SpreadSheet"
		}
	    }
        }
        const data = await req.callWithHeader(config);
    }

    async sheetChange(accessToken, info) {
        const head = 'Bearer ' + accessToken
        const uri = "https://www.googleapis.com/drive/v3/files/" + info.id + "?fields=name,modifiedTime&key=" + key.google.APIKey
        const config = {
            url: uri,
            method: 'get',
            headers: {
                'Authorization' : head,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }
        var data = await req.callWithHeader(config);
	var toReturn = {
		title : "Spread sheet " + data.name + " modified",
		text : "Your spread sheet named " + data.name + " has been modified at " + data.modifiedTime,
		date : data.modifiedTime,
		name : data.name,
		bool : true
	}
	toReturn.bool = (Date.parse(data.modifiedTime) > info.date)
        return toReturn;
    }
}
