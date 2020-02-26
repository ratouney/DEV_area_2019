import Request from "./requestClass"
import key from "./key"

const req = new Request();
const baseUrl = "https://api.spotify.com/v1/me/player/"

export class SpotifyAPI {

    constructor() {}

    async pauseSong(accessToken : string, info) {
        const head = "Bearer " + accessToken;
        const uri = baseUrl + "pause";
        const config = {
            url : uri,
            method : 'put',
            headers : {
                'Accept' : 'application/json',
                'Authorization' : head
            }
        }
        await req.callWithHeader(config);
    }

    async nextSong(accessToken, info) {
        const head = 'Bearer ' + accessToken
        const uri = baseUrl + "next"
        const config = {
            url : uri,
            method : 'post',
            headers : {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        await req.callWithHeader(config);
    }

    async prevSong(accessToken, info) {
        const head = 'Bearer ' + accessToken
        const uri = baseUrl + "previous"
        const config = {
            url : uri,
            method : 'post',
            headers : {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        await req.callWithHeader(config);
    }

    async setVolume(userId, info) {
        const head = 'Bearer ' + userId
        const uri = "https://api.spotify.com/v1/me/player/volume?volume_percent=" + info.data
        const config = {
            url : uri,
            method : 'put',
            headers : {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        const data = await req.callWithHeader(config);
    }
}
