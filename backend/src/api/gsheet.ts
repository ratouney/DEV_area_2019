import Request from "./requestClass"
import key from "./key"

const req = new Request();

export class GoogleSheetAPI {

    async createSheet(accessToken) {
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
            data: {}
        }
        await req.callWithHeader(config);
    }

    async sheetChange(accessToken, sheetId, lastDate) {
        const head = 'Bearer ' + accessToken
        const uri = "https://www.googleapis.com/drive/v3/files/" + sheetId + "?fields=modifiedTime&key=" + key.google.APIKey
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
        console.log(data);
        /*
        const timeStamp = new Date(data.modifiedTime).getTime();
        console.log(timeStamp)
        console.log(Date.parse(data.modifiedTime))
        */
        return true;
    }
}
