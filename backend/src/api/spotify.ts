import request from "./requestsClass"
import key from "./key"

export class SpotifyAPI {

    constructor() {}

    async pauseSong(accessToken) {
        const head = "Bearer " + accessToken
        const config = {
            url : "https://api.spotify.com/v1/me/player/pause",
            method : 'put',
            header : {
                'Accept : application/json',
                'Authorization' : head
            }
        }
        const data = await request.callWithHeader(config);
        console.log(data);
        return data;
    }

    async nextSong(accessToken) {
        const head = 'Bearer ' + accessToken
        const config = {
            url : "https://api.spotify.com/v1/me/player/next",
            method: 'post',
            header: {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        const data = await request.callWithHeader(config);
        console.log(data);
        return data;
    }

    async prevSong(userId) {
        const head = 'Bearer ' + userId
        const config = {
            url : "https://api.spotify.com/v1/me/player/previous",
            method: 'post',
            header: {
                'Authorization': head,
                'Accept': 'application/json'
            }
        }
        const data = await request.callWithHeader(config);
        console.log(data);
        return data;
    }
}
