const MIMEText = require("mimetext")
import Request from "./requestClass"
import key from "./key"

const urlBase = "https://www.googleapis.com/gmail/"
const req = new Request();

export class GmailAPI {

    createMessage(sender, to, subject, body) {
        const message = new MIMEText()

        message.setSender(sender)
        message.setRecipient(to)
        message.setSubject(subject)
        message.setMessage(body)
        return message
    }

    async sendMessage(googleId, info) {
        //const message = this.createMessage(info.senderMail, info.destMail, info.title, info.text);
        const message = new MIMEText();
        message.setSender(info.senderMail);
        message.setRecipient(info.destMail);
        message.setSubject(info.title);
        message.setMessage(info.text || info.url);
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
        const rt = await req.callWithHeader(config);
        console.log("SendMessage RT : ", rt);
    }

    async createDraft(googleId, info) {
        //const message = this.createMessage(info.senderMail, info.destMail, info.title, info.text);
        const message = new MIMEText();
        message.setSender(info.senderMail);
        message.setRecipient(info.destMail);
        message.setSubject(info.title);
        message.setMessage(info.text || info.url);
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

    async hasGotNewMsg(googleId, info) {
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
	var toReturn = {
		bool : false,
		id : info.id
	}
	if (data.messages.id > info.id) {
		toReturn.bool = true
		toReturn.id = data.messages.id
	}
	return toReturn;
    }
}