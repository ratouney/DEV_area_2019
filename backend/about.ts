export function info(host, time) {
    return {
        "client" :  {
            "host" : host
        },
        "server" : {
            "current_time" : time,
            "services": [{
                "name" : "imgur",
                "actions" : [{
                    "name" : "NewPicForTag",
                    "description" : "A new picture has been added with defined tag"
                }, {
                    "name" : "getNewFav",
                    "description" : "User defined has favorite a new picture"
                }, {
                    "name" : "getNewVote",
                    "description" : "A post given got new votes"
                }, {
                    "name" : "getNewComment",
                    "description" : "A post given got new comment"
                }],
                "reactions" : [{
                    "name" : "uploadPic",
                    "description" : "Upload a new picture with title and description"
                }, {
                    "name" : "changeBio",
                    "description" : "Update user info"
                }]
            }, {
                "name" : "weather",
                "actions" : [{
                    "name" : "weatherByCity",
                    "description" : "Retrieve today weather for a given city"
                }, {
                    "name" : "uvLimitReached",
                    "description" : "Check if a given UV limit has been reached for a given city"
                }],
                "reactions" : []
            }, {
                "name" : "spotify",
                "actions" : [],
                "reactions" : [{
                    "name" : "changePlayerState",
                    "description" : "Pause the current song, go to the next one or the previous one"
                }, {
                    "name" : "setVolume",
                    "description" : "Update current player volume"
                }]
            }, {
                "name" : "nasa",
                "actions" : [{
                    "name": "todayPicture",
                    "description" : "Get picture of the day posted by NASA"
                }, {
                    "name" : "marsPhoto",
                    "description" : "Get Mars picture from NASA among 855 elements"
                }],
                "reactions" : []
            }, {
                "name" : "pokemon",
                "actions" : [{
                    "name" : "getPokemonByName",
                    "description" : "Retrieve info about a pokemon among 700 elements"
                }],
                "reactions" : []
            }, {
                "name" : "googleSheet",
                "actions" : [{
                    "name" : "sheetChange",
                    "description" : "A given spreadsheet as been updated"
                }],
                "reactions" : [{
                    "name" : "createSheet",
                    "description" : "Create a new spreadsheet with name if given"
                }]
            }, {
                "name" : "googleMail",
                "actions" : [{
                    "name" : "newMail",
                    "description" : "A new mail has been received"
                }],
                "reactions" : [{
                    "name" : "sendMessage",
                    "description" : "Send a mail to given person with given object and given body"
                }, {
                    "name" : "createDraft",
                    "description" : "Create draft with given object, body and receiver"
                }]
            }]
        }
    }
}