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
        const data = await req.callWithHeader(config);
        console.log(data);
        return data;
    }
}
