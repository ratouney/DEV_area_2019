import Request from "./requestClass"
import key from "./key"

const req = new Request()

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
        req.callWithHeader(config);
    }

    async getNewVote(picHash, isAlbum, nbVotes) {
        const head = "Client-ID " + key.imgur.clientID
        const uri = "https://api.imgur.com/3/gallery/" + (isAlbum ? "album/" : "image/") + picHash + "/comments/new"
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        }
        const data = await req.callWithHeader(config);
        return (data.ups + data.downs) > nbVotes;
    }

    async getNewComment(picHash, isAlbum, nbComments) {
        const head = "Client-ID " + key.imgur.clientID
        const uri = "https://api.imgur.com/3/gallery/" + (isAlbum ? "album/" : "image/") + picHash + "/votes"
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        }
        const data = await req.callWithHeader(config);
        return data.comment_count > nbComments;
    }

    async isThereNewPicForTag(tagName, nbForTag) {
        const head = "Client-ID " + key.imgur.clientID
        const uri = "https://api.imgur.com/3/gallery/tag_info/" + tagName
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        }
        const data = await req.callWithHeader(config);
        console.log(data)
        return data.total_items > nbForTag;
    }

    async userGotNewFav(accessToken, userName, nbFav) {
        const head = "Bearer " + accessToken
        const uri = "https://api.imgur.com/3/account/" + userName + "/favorites//newest"
        const config = {
            url: uri,
            method: "get",
            headers : {
                'Authorization' : head,
            }
        };
        const data = await req.callWithHeader(config);
        //check with api
        return true;
    }

    async changeUserBio(accessToken, userName, newBio) {
        const head = "Bearer " + accessToken
        const uri = "https://api.imgur.com/3/account/" + userName + "/settings"
        const config = {
            url: uri,
            method: "post",
            headers : {
                'Authorization' : head,
            },
            data: {
                "bio": newBio,
            }
        };
        await req.callWithHeader(config);
    }
}
