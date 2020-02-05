import Request from "./requestClass"
import key from "./key"

const req = new Request();

export class SpotifyAPI {

    constructor() {}

    async pauseSong(accessToken : string) {
        const head = "Bearer " + accessToken
        console.log(head)
        const config = {
            url : "https://api.spotify.com/v1/me/player/pause",
            method : 'put',
            headers : {
                'Accept' : 'application/json',
                'Authorization' : head
            }
        }
        const data = await req.callWithHeader(config);
        console.log(data);
        return data;
    }

    async nextSong(accessToken) {
        const head = 'Bearer ' + accessToken
        const config = {
            url : "https://api.spotify.com/v1/me/player/next",
            method : 'post',
            headers : {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        const data = await req.callWithHeader(config);
        console.log(data);
        return data;
    }

    async prevSong(userId) {
        const head = 'Bearer ' + userId
        const config = {
            url : "https://api.spotify.com/v1/me/player/previous",
            method : 'post',
            headers : {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        const data = await req.callWithHeader(config);
        console.log(data);
        return data;
    }

    async setVolume(userId, volume) {
        const head = 'Bearer ' + userId
        const uri = "https://api.spotify.com/v1/me/player/volume?volume_percent=" + volume
        const config = {
            url : uri,
            method : 'post',
            headers : {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        const data = await req.callWithHeader(config);
        console.log(data);
        return data;
    }
}
