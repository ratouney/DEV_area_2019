const MIMEText = require("mimetext")
import Request from "./requestClass"
import key from "./key"

const urlBase = "https://www.googleapis.com/gmail/"
const req = new Request();

export class GmailAPI {

    private createMessage(sender, to, subject, body) {
        const message = new MIMEText()

        message.setSender(sender)
        message.setRecipient(to)
        message.setSubject(subject)
        message.setMessage(body)
        return message
    }

    async sendMessage(googleId, sender, to, subject, body) {
        const message = this.createMessage(sender, to, subject, body);
        console.log(message)
        const head = 'Bearer ' + googleId
        const uri = urlBase + "v1/users/me/messages/send?key=" + key.google.APIKey
        const config = {
            url: uri,
            method: 'post',
            headers: {
                'Authorization' : head,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {
                'raw': message.asEncoded()
            }
        }
        await req.callWithHeader(config);
    }

    async createDraft(googleId, sender, to, subject, body) {
        const message = this.createMessage(sender, to, subject, body);
        console.log(message)
        const head = 'Bearer ' + googleId
        const uri = urlBase + "v1/users/me/drafts?key=" + key.google.APIKey
        const config = {
            url: uri,
            method: 'post',
            headers: {
                'Authorization' : head,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: {
                "message": {
                    'raw': message.asEncoded()
                }
            }
        }
        await req.callWithHeader(config);
    }

    async hasGotNewMsg(googleId, lastMessageId) {
        const uri = urlBase + "v1/users/me/messages?maxResults=1&key=" + key.google.APIKey
        const head = 'Bearer ' + googleId
        const config = {
            url: uri,
            method: 'get',
            headers: {
                'Authorization' : head,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }
        const data = await req.callWithHeader(config);
        console.log("BITE : ", data.response.data);
        return (data.messages.id > lastMessageId)
    }
}
