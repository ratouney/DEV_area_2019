import Request from "./requestClass"
import key from "./key"

const req = new Request()

export class ImgurAPI {
    
    async uploadPic(accessToken, info) {
        const head = "Bearer " + accessToken
        console.log(info)
        const config = {
            url: "https://api.imgur.com/3/image",
            method: "post",
            headers : {
                'Authorization' : head,
            },
            data: {
                "image": info.url,
                "title" : info.title,
                "description" : info.text
            }
        }
        const data = await req.callWithHeader(config);
    }
    
    async getNewComment(accessToken, info) {
        const head = "Client-ID " + key.imgur.clientID
        const uri = "https://api.imgur.com/3/gallery/" + info.id
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        }
        const data = await req.callWithHeader(config);
        const toReturn = {
            title : "New comment",
            text : "The picture named" + data.title + " got new comment",
            bool : data.comment_count > info.data,
            data : data.comment_count
        }
        return toReturn
    }
    
    async getNewVote(accessToken, info) {
        const head = "Client-ID " + key.imgur.clientID
        const uri = "https://api.imgur.com/3/gallery/"+ info.id + "/votes"
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        }
        const data = await req.callWithHeader(config);
        const toReturn = {
            title : "New vote",
            text : "The picture with id " + info.id + " got new vote",
            bool : (data.ups - data.downs) > info.data,
            data : (data.ups - data.downs)
        }
        return toReturn;
    }
    
    async isThereNewPicForTag(accessToken, info) {
        const head = "Client-ID " + key.imgur.clientID
        const uri = "https://api.imgur.com/3/gallery/tag_info/" + info.name
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        }
        const data = await req.callWithHeader(config);
        const toReturn = {
            title : "New picture with tag " + info.name,
            texte : "A new picture has been posted with tag : " + info.name,
            bool : data.total_items > info.data,
            data : data.total_items
        }
        return toReturn
    }
    
    async userGotNewFav(accessToken, info) { //userName, nbFav) {
        const head = "Bearer " + accessToken
        const uri = "https://api.imgur.com/3/account/" + info.name + "/gallery_profile"
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        };
        const data = await req.callWithHeader(config);
        console.log(data)
        const toReturn = {
            title : "New favorites",
            text : "The user " + info.name + " has added a new picture to his favorites",
            bool : data.total_gallery_favorites > info.data,
            data : data.total_gallery_favorites
        }
        return toReturn;
    }
    
    async changeUserBio(accessToken, info) {
        const head = "Bearer " + accessToken
        const uri = "https://api.imgur.com/3/account/" + info.name + "/settings"
        const config = {
            url: uri,
            method: "post",
            headers : {
                'Authorization' : head,
            },
            data: {
                "bio": info.text,
            }
        };
        const data = await req.callWithHeader(config);
        console.log(data)
    }
}
