<template>
    <html>
    <div id="area">
        <div id="navbar" class="navbar">
            <div style="max-width: 90%; margin: auto;">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                <a href="dashboard"></a>
                <div class="topnav-right">
                    <a href="#" @click="$router.push('/')">Disconnect</a>
                </div>
            </div>
        </div>
        <div>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>jQuery UI Menu - Default functionality</title>
            <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
            <link rel="stylesheet" href="/resources/demos/style.css">
            <button class="GConnect" @click="imgurlogin">Login with imgur</button>

            <div v-if="!see">
            <h1>Create Area</h1>

            <!--========================================= Début cardview 1 ===================================-->
            <div class="portlet">
                <div class="portlet-header">Select your action</div>
                <div class="portlet-content">

                    <!--========================================= Début liste Actions ===================================-->
                    <label for="Action"></label>

                    <select @change="onChangeAction($event)" class="form-control"  id="action">
                        <option value="GMailGetMail">New e-mail</option>
                        <option value="SheetUpdated">Change Sheet</option>
                        <option value="nasa.todayPicture">Daily Photo</option>
                        <option value="imgur.isThereNewPicForTag">New pic in the tag</option>
                        <option value="imgur.getNewVote">New vote</option>
                        <option value="NewComment">New Comment</option>
                        <option value="imgur.userGotNewFav">New Favorite</option>
                        <option value="UV">UV limit</option>
                        <option value="Weather">Daily Weather</option>
                        <option value="pokemon.getPokemonByName">Get a Pokemon</option>
                    </select>

                    <div v-if="this.ActionValue === 'GMailGetMail'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit" class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Gmail</h1>
                            <p>When receiving a mail</p>
                        </div>
                    </div>

                    <div v-if="this.ActionValue === 'SheetUpdated'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit" class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Google Sheet</h1>
                            <p>A spreadsheet has been updated</p>
                            <input type="text" id="w3mission" style="width: 30%; font-size: 17px;" name="link" value="Link of the sheet" required>
                        </div>
                    </div>

                    <div v-if="this.ActionValue === 'nasa.todayPicture'" style="display:block;">
                        <h1>Nasa</h1>
                        <p>Take the daily mars picture</p>
                    </div>

                    <div v-if="this.ActionValue === 'imgur.isThereNewPicForTag'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>A picture with a specific tag has been uploaded</p>
                        <input v-model="name" type="text" id="w3mission" style="width: 10%; font-size: 17px; text-align: center;" name="email" value="tag name" required>
                    </div>

                    <div v-if="this.ActionValue === 'imgur.getNewVote'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>When the specified picture get a new vote</p>
                        <input v-model="id" type="text" id="w3mission" style="width: 30%; font-size: 17px;" name="link" value="Imgur link of the pic" required>
                    </div>

                    <div v-if="this.ActionValue === 'NewComment'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>When the specified picture get a new comment</p>
                        <input type="text" id="w3mission" style="width: 30%; font-size: 17px;" name="link" value="Imgur link of the pic" required>
                    </div>

                    <div v-if="this.ActionValue === 'imgur.userGotNewFav'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>When the specified picture get a new fav</p>
                        <input v-model="name" type="text" id="w3mission" style="width: 15%; font-size: 17px; text-align: center;" name="link" value="Account Name" required>
                    </div>

                    <div v-if="this.ActionValue === 'limitUV'" style="display:block;">
                        <h1>Weather</h1>
                        <p>Check if a UV limit has been reached for a city</p>
                        <input type="text" id="w3mission" style="width: 15%; font-size: 17px; text-align: center;" name="city" value="City" required>
                        <br>
                        <input type="number" id="w3mission" style="width: 5%; font-size: 20px; text-align: center;" name="UV" value="10" required>
                    </div>

                    <div v-if="this.ActionValue === 'dailyWeather'" style="display:block;">
                        <h1>Weather</h1>
                        <p>The weather of the day for a city</p>
                        <input type="text" id="w3mission" style="width: 15%; font-size: 17px; text-align: center;" name="city" value="City" required>
                    </div>

                    <div v-if="this.ActionValue === 'pokemon.getPokemonByName'" style="display:block;">
                        <h1>Pokemon</h1>
                        <p>Get a random Pokemon</p>
                    </div>
                    <!--========================================= Fin liste Actions ===================================-->

                </div>
            </div>

            <!--========================================= Fin cardview 1 ===================================-->

            <!--========================================= Début cardview 2 ===================================-->
            <div class="portlet">
                <div class="portlet-header">Select your reaction</div>
                <div class="portlet-content">

                    <!--========================================= Début liste Reactions ===================================-->

                    <label for="Reaction"></label>

                    <select @change="onChangeReaction($event)" class="form-control" id="reaction">
                        <option value="gmail.sendMessage">Send an e-mail</option>
                        <option value="SheetCreateNew">Create a Sheet</option>
                        <option value="gmail.createDraft">create a draft</option>
                        <option value="Volume">Set the volume</option>
                        <option value="Pause">Music on pause</option>
                        <option value="spotify.nextSong">Play next song</option>
                        <option value="UploadPicture">Upload a pic</option>
                        <option value="ChangeBio">Change Bio</option>
                    </select>

                    <div v-if="this.ReactionValue === 'gmail.sendMessage'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit " class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Gmail</h1>
                            <p>Send a mail</p>
                            <input v-model="destMail" type="email" id="w3mission" style="width: 30%;" name="email" value="Send to" required>
                            <br>
                            <input v-model="title" type="text" id="w3mission" style="width: 30%;" name="objet" value="Subject" required>
                            <div style="display:block;">
                                <textarea v-model="text" id="w3mission" rows="6" cols="65">
                                    Votre texte
                                </textarea>
                            </div>
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'SheetCreateNew'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit" class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Google Sheet</h1>
                            <p>Create a new spreadsheet</p>
                            <input type="text" id="w3mission" style="width: 30%; font-size: 17px;" name="email" value="Title of the sheet" required>
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'gmail.createDraft'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit " class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Gmail</h1>
                            <p>Send a mail</p>
                            <input v-model="destMail" type="email" id="w3mission" style="width: 30%;" name="email" value="Send to" required>
                            <br>
                            <input v-model="title" type="text" id="w3mission" style="width: 30%;" name="objet" value="Subject" required>
                            <div style="display:block;">
                                <textarea v-model="text" id="w3mission" rows="6" cols="65">
                                    Votre texte
                                </textarea>
                            </div>
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'Volume'" style="display:block;">
                        <button v-if="spotifyAccessToken == ''" id="btn-login" class="GConnect" @click="spotifyLogin()">Login with Spotify</button>
                        <div v-if="spotifyAccessToken !== ''">
                            <h1>Spotify</h1>
                            <p>Set the volume in Spotify</p>
                            <input type="number" id="w3mission" style="width: 5%; font-size: 17px; text-align: center;" name="volume" min="0" max="100" value="50" required>
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'spotify.nextSong'" style="display:block;">
                        <button v-if="spotifyAccessToken == ''" id="btn-login" class="GConnect" @click="spotifyLogin()">Login with Spotify</button>
                        <div v-if="spotifyAccessToken !== ''">
                            <h1>Spotify</h1>
                            <p>Play next song in your playlist</p>
                        </div>
                    </div>


                    <div v-if="this.ReactionValue === 'Pause'" style="display:block;">
                        <button v-if="spotifyAccessToken == ''" id="btn-login" class="GConnect" @click="spotifyLogin()">Login with Spotify</button>
                        <div v-if="spotifyAccessToken !== ''">

                            <h1>Spotify</h1>
                            <p>Pause the song</p>
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'UploadPicture'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>Upload a picture</p>
                        <input type="text" id="w3mission" style="width: 30%;" name="email" value="link of the pic" required>
                            <br>
                            <input type="text" id="w3mission" style="width: 30%; font-size: 17px;" name="objet" value="Title" required>
                            <div style="display:block;">
                                <textarea id="w3mission" rows="6" cols="65">
                                    Description of your picture
                                </textarea>
                            </div>
                    </div>

                    <div v-if="this.ReactionValue === 'ChangeBio'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>Change the bio</p>
                        <div style="display:block;">
                            <textarea id="w3mission" rows="6" cols="65">
                                New biographie for your account
                            </textarea>
                        </div>
                    </div>

                    <!--========================================= Fin liste Reactions ===================================-->

                </div>
            </div>

            <!--========================================= Fin cardview 2 ===================================-->


            <button @click="createArea()" style="width:25%; background-color: #4CAF50; font-size: 30px;">Confirm</button> <!-- bouton d'envoie -->
            <button @click="seeArea()" style="width:25%; background-color: #4CAF50; font-size: 30px;">See Area</button>
        </div>
            <div v-if="see">
                <h1>See your Area</h1>
                <div v-html="legacySystemHTML"></div>
                <button @click="see = false" style="width:25%; background-color: #4CAF50; font-size: 30px;">Manage Area</button>
                <!--========================================= Début cardview 1 ===================================-->
            </div>
        </div>
    </div>
</html>
</template>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$( function() {
    $( "#menu" ).menu();
} );
</script>
<script>
export default {
    name: 'test',
    props: [],
    components: {
    },
    data () {
        return {
            googleAccessToken: '',
            userToken: '',
            spotifyAccessToken: '',
            id: '',
            googleMail: '',
            url :'',
            destMail : '',
            bolien :'',
            title :'',
            name :'',
            data :'',
            text :'',
            ActionValue: 'GMailGetMail',
            ReactionValue: 'gmail.sendMessage',
            legacySystemHTML: '',
            see: false,
            Area: null,
            Action: null,
            Reaction: null,
            Services: null,
            isInit: false
        }
    },
    computed: {
    },
    created() {
        let that = this
        this.googleAccessToken = this.$route.query.google
        if (!this.googleAccessToken)
        this.googleAccessToken = ''

        console.log(this.googleAccessToken)
        if(this.$route.query.debug) {
            this.userToken = this.$route.query.debug;
        }
        var requestOptions = {
          method: 'GET',
          redirect: 'follow'
        };

        fetch("/service", requestOptions)
          .then(response => response.text())
          .then(function(result) {
              console.log("TEEEEEST")
              that.Services = JSON.parse(result).data
              console.log(that.Services)
          })
          .catch(error => console.log('error', error));
    },
    methods: {
        onChangeAction(event) {
            let self = this
            self.ActionValue = event.target.value
        },
        onChangeReaction(event) {
            let self = this
            self.ReactionValue = event.target.value
        },
        GoogleLogin(){
            let self = this
            let that = this
            let service = null
            this.$gAuth.signIn(function (user) {
                self.googleMail = user.Qt.zu
                self.googleAccessToken = user.uc.access_token
                let i = 0;

                for (i = 0; that.Services[i]; i++) {
                    if (that.Services[i].name == "Gmail" || that.Services[i].name == "Sheet") {
                        console.log("testtesttest")
                        var myHeaders = new Headers();
                        myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

                        var urlencoded = new URLSearchParams();
                        urlencoded.append("serviceId", that.Services[i].id);
                        urlencoded.append("token", that.googleAccessToken);

                        var requestOptions = {
                            method: 'POST',
                            headers: myHeaders,
                            body: urlencoded,
                            redirect: 'follow'
                        };

                        fetch("/token/new?token=" + that.userToken, requestOptions)
                        .then(response => response.text())
                        .then(result => console.log(result))
                        .catch(error => console.log('error', error));
                    }
                }
            })
        },
        seeArea() {
            let that = this
            let i = 0
            let j = 0
            var data = {
                url :that.url,
                senderMail :that.googleMail,
                destMail : that.destMail,
                bool :that.bolien,
                title :that.title,
                name :that.name,
                data :that.data,
                text :that.text,
                id :that.id
            }
            console.log(data)

            var requestOptions = {
                method: 'GET',
                redirect: 'follow'
            };

            fetch("/area/me?token=" + this.userToken, requestOptions)
            .then(response => response.text())
            .then(function(result) {
                console.log(JSON.parse(result).data)
                that.Area = JSON.parse(result).data
            })
            .catch(error => console.log('error', error));



            this.legacySystemHTML = ''
            this.see = true;
            for (i = 0; that.Area[i]; i++) {
                j = i + 1
                that.legacySystemHTML += "<div class='portlet'>" +
                "<div class='portlet-header'>Area " + j + "</div>" + "<div class='portlet-content'>" +
                                        "<label for='Action'></label>" +
                                        "<div style='display:block;'>" + 'When you ' + that.Area[i].action.description + ' then ' + that.Area[i].reaction.description +
                                        "</div></div></div>"
 //+ that.Area[i].action.description + ' then' + that.Area[i].reaction.description + '</MARQUEE></FONT>'
            }
        },
        imgurlogin () {

            let that = this
                var CLIENT_ID = '094ee1cffcac340';
                var REDIRECT_URI = 'https://api.imgur.com/oauth2/authorize?response_type=token&client_id=094ee1cffcac340';
                function getLoginURL(scopes) {
                    return REDIRECT_URI /*REDIRECT_URI
https://api.imgur.com/oauth2/authorize?client_id=CLIENT_ID&response_type=token

                    'https://api.imgur.com/oauth2/authorize?client_id=' + CLIENT_ID +
                    '&response_type=token'*/;
                }

                var url = getLoginURL([
                    'user-read-email'
                ]);
                console.log(url)

                var width = 450,
                height = 730,
                left = (screen.width / 2) - (width / 2),
                top = (screen.height / 2) - (height / 2);

                window.addEventListener("message", function(event) {
                    var hash = JSON.parse(event.data);
                    if (hash.type == 'access_token') {
                        callback(hash.access_token);
                    }
                }, false);
                var w = window.open(url,
                    'Imgur',
                    'menubar=no,location=no,resizable=no,scrollbars=no,status=no, width=' + width + ', height=' + height + ', top=' + top + ', left=' + left
                );
                var myvar = setInterval(alertFunc, 500);
                function alertFunc() {
                    var hash = w.location.hash; //Puts hash in variable, and removes the # character
                    const word = hash.split('&')
                    console.log(hash)
                    console.log(word[0])
                    that.spotifyAccessToken = word[0]
                    if (hash) {
                        clearInterval(myvar)
                        w.close()
                    }
                }
        },
        spotifyLogin() {
            let that = this
                var CLIENT_ID = 'd3ebae5610894ca48c9f66794214252b';
                var REDIRECT_URI = 'http://localhost:8080/spotify';
                function getLoginURL(scopes) {
                    return 'https://accounts.spotify.com/authorize?client_id=' + CLIENT_ID +
                    '&redirect_uri=' + encodeURIComponent(REDIRECT_URI) +
                    '&scope=' + encodeURIComponent(scopes.join('')) +
                    '&response_type=token';
                }

                var url = getLoginURL([
                    'user-read-email' + ' ' + 'user-modify-playback-state'
                ]);
                var width = 450,
                height = 730,
                left = (screen.width / 2) - (width / 2),
                top = (screen.height / 2) - (height / 2);

                window.addEventListener("message", function(event) {
                    var hash = JSON.parse(event.data);
                    if (hash.type == 'access_token') {
                        callback(hash.access_token);
                    }
                }, false);
                var w = window.open(url,
                    'Spotify',
                    'menubar=no,location=no,resizable=no,scrollbars=no,status=no, width=' + width + ', height=' + height + ', top=' + top + ', left=' + left
                );
                var myvar = setInterval(alertFunc, 500);
                function alertFunc() {
                    var hash = w.location.hash.substring(15); //Puts hash in variable, and removes the # character
                    const word = hash.split('&')
                    console.log(word[0])
                    that.spotifyAccessToken = word[0]
                    if (hash) {
                        clearInterval(myvar)
                        w.close()
                        let i = 0;
                        for (i = 0; that.Services[i]; i++) {
                            if (that.Services[i].name == "Spotify") {
                                console.log("testtesttest")
                                var myHeaders = new Headers();
                                myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

                                var urlencoded = new URLSearchParams();
                                urlencoded.append("serviceId", that.Services[i].id);
                                urlencoded.append("token", that.spotifyAccessToken);

                                var requestOptions = {
                                    method: 'POST',
                                    headers: myHeaders,
                                    body: urlencoded,
                                    redirect: 'follow'
                                };

                                fetch("/token/new?token=" + that.userToken, requestOptions)
                                .then(response => response.text())
                                .then(result => console.log(result))
                                .catch(error => console.log('error', error));
                            }
                        }
                    }
                }

        },
        createArea() {
            let that = this
            let i = 0
            let act = -1
            let react = -1
            console.log(that.Action)
            for (i = 0; that.Action[i]; i++) {
                if (that.Action[i].name == that.ActionValue)
                act = i
                console.log(that.Action[i])
            }
            console.log(that.Reaction)
            for (i = 0; that.Reaction[i]; i++) {
                if (that.Reaction[i].name == that.ReactionValue)
                react = i
                console.log(that.Reaction[i])
            }

            var data = {
                url :that.url,
                senderMail :that.googleMail,
                destMail : that.destMail,
                bool :that.bolien,
                title :that.title,
                name :that.name,
                data :that.data,
                text :that.text,
                id :that.id
            }
            console.log(data)

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

            var urlencoded = new URLSearchParams();
            urlencoded.append("actionId", that.Action[act].id);
            urlencoded.append("reactionId", that.Reaction[react].id);
            urlencoded.append("name", "I " + that.Action[act].name + "and " + that.Reaction[react].name + that.userToken);
            urlencoded.append("timeCheck", "-1");
            urlencoded.append("data", JSON.stringify(data))

            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: urlencoded,
                redirect: 'follow'
            };

            fetch("/area/new?token=" + this.userToken, requestOptions)
            .then(response => response.text())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));

            var requestOptions = {
                method: 'GET',
                redirect: 'follow'
            };

            fetch("/area/me?token=" + this.userToken, requestOptions)
            .then(response => response.text())
            .then(function(result) {
                console.log(JSON.parse(result).data)
                that.Area = JSON.parse(result).data
            })
            .catch(error => console.log('error', error));

        },
    },
    mounted(){
        let that = this
        if (that.userToken == '')
            $router.push('/')
        var requestOptions = {
            method: 'GET',
            redirect: 'follow'
        };

        fetch("/area/action?serviceId", requestOptions)
        .then(response => response.text())
        .then(function(result) {
            that.Action = JSON.parse(result).data
            console.log(JSON.parse(result).data)
        })
        .catch(error => console.log('error', error));

        var requestOptions = {
            method: 'GET',
            redirect: 'follow'
        };

        fetch("/area/reaction", requestOptions)
        .then(response => response.text())
        .then(function(result) {
            that.Reaction = JSON.parse(result).data
            console.log(JSON.parse(result).data)
        })
        .catch(error => console.log('error', error));


        var requestOptions = {
            method: 'GET',
            redirect: 'follow'
        };

        fetch("/area/me?token=" + that.userToken, requestOptions)
        .then(response => response.text())
        .then(function(result) {
            that.Area = JSON.parse(result).data
            console.log(that.Area)
        })
        .catch(error => console.log('error', error));


        let checkGauthLoad = setInterval(function(){
            that.isInit = that.$gAuth.isInit
            console.log('checked', that.isInit)
            if(that.isInit) clearInterval(checkGauthLoad)
        }, 1000);
    },
}
</script>
