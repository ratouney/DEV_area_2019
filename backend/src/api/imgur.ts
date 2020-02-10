import Request from "./requestClass"
import key from "./key"

export class ImgurAPI {

    async uploadPic(accessToken, url) {
        const head = "Bearer " + accessToken
        const config = {
            url: "https://api.imgur.com/3/image",
            method: "post",
            headers : {
                'Authorization' : head,
            },
            data: {
                "image": url,
            }
        }
    }
}
