import sheet from '../../../../app/src/main/res/drawable/sheet.png';
import pokemon from '../../../../app/src/main/res/drawable/pokemon.png';
import { Action } from '../../../backend/src/entities';
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
                        <option value="NasaDailyMars">Daily Photo</option>
                        <option value="NewPicForTag">New pic in the tag</option>
                        <option value="newVote">New vote</option>
                        <option value="NewComment">New Comment</option>
                        <option value="NewFav">New Favorite</option>
                        <!--<option value="limitUV">UV limit</option>           mis en commentaire car pas dans le postman
                        <option value="dailyWeather">Daily Weather</option>-->
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
                        </div>
                    </div>

                    <div v-if="this.ActionValue === 'NasaDailyMars'" style="display:block;">
                        <h1>Nasa</h1>
                        <p>Take the daily mars picture</p>
                    </div>

                    <div v-if="this.ActionValue === 'NewPicForTag'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>A picture with a specific tag has been uploaded</p>
                    </div>

                    <div v-if="this.ActionValue === 'newVote'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>When the specified picture get a new vote</p>
                    </div>

                    <div v-if="this.ActionValue === 'NewComment'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>When the specified picture get a new comment</p>
                    </div>

                    <div v-if="this.ActionValue === 'NewFav'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>When the specified picture get a new fav</p>
                    </div>

                    <!--<div v-if="this.ActionValue === 'limitUV'" style="display:block;">      mis en commentaire car pas dans le postman
                        <h1>Weather</h1>
                        <p>Limit UV description</p>
                    </div>

                    <div v-if="this.ActionValue === 'dailyWeather'" style="display:block;">
                        <h1>Weather</h1>
                        <p>The weather of the day</p>
                    </div>-->
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
                        <option value="GMailSendMail">Send an e-mail</option>
                        <option value="SheetCreateNew">Create a Sheet</option>
                        <option value="createDraft">create a draft</option>
                        <option value="Volume">Set the volume</option>
                        <option value="Pause">Music on pause</option>
                        <option value="UploadPicture">Upload a pic</option>
                        <option value="ChangeBio">Change Bio</option>
                    </select>

                    <div v-if="this.ReactionValue === 'GMailSendMail'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit " class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Gmail</h1>
                            <p>Send a mail</p>
                            <div style="display:block;">
                                <textarea id="w3mission" rows="4" cols="50">
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
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'createDraft'" style="display:block;">
                        <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isInit" class="GConnect"><i class="fa fa-google fa-fw"></i>Sign in with Google</button>
                        <div v-if="googleAccessToken !== ''">
                            <h1>Gmail</h1>
                            <p>Create a draft</p>
                            <div style="display:block;">
                                <textarea id="w3mission" rows="4" cols="50">
                                    Votre texte
                                </textarea>
                            </div>
                        </div>
                    </div>

                    <div v-if="this.ReactionValue === 'Volume'" style="display:block;">
                        <h1>Spotify</h1>
                        <p>Set the volume in Spotify</p>
                    </div>

                    <div v-if="this.ReactionValue === 'Pause'" style="display:block;">
                        <h1>Spotify</h1>
                        <p>Pause the song</p>
                    </div>

                    <div v-if="this.ReactionValue === 'UploadPicture'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>Upload a picture</p>
                    </div>

                    <div v-if="this.ReactionValue === 'ChangeBio'" style="display:block;">
                        <h1>Imgur</h1>
                        <p>Change the bio</p>
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
            id: '',
            googleMail: '',
            ActionValue: 'GMailGetMail',
            ReactionValue: 'GMailSendMail',
            legacySystemHTML: '',
            see: false,
            Area: null,
            Action: null,
            Reaction: null,
            isInit: false
        }
    },
    computed: {
    },
    created() {
        this.id = this.$route.params.id;
        if(this.$route.query.debug) {
            this.userToken = this.$route.query.debug;
        }
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
            this.$gAuth.signIn(function (user) {
                self.googleMail = user.Qt.zu
                self.googleAccessToken = user.uc.access_token
            }, function (error) {
            })
        },
        test() {

            (function() {

                function login(callback) {
                    var CLIENT_ID = 'c299f837f4ff4872ab27a1a00a6c7bdf';
                    var REDIRECT_URI = 'http://localhost8080/#/area/api/spotify';
                    function getLoginURL(scopes) {
                        return 'https://accounts.spotify.com/authorize?client_id=' + CLIENT_ID +
                        '&redirect_uri=' + encodeURIComponent(REDIRECT_URI) +
                        '&scope=' + encodeURIComponent(scopes.join(' ')) +
                        '&response_type=token';
                    }

                    var url = getLoginURL([
                        'user-read-email'
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
                    console.log(callback)


                }

                function getUserData(accessToken) {
                    return $.ajax({
                        url: 'https://api.spotify.com/v1/me',
                        headers: {
                            'Authorization': 'Bearer ' + accessToken
                        }
                    });
                }

                var loginButton = document.getElementById('btn-login');

                loginButton.addEventListener('click', function() {
                    console.log('test')
                    login(function(accessToken) {
                        console.log('test1')
                        getUserData(accessToken)
                        .then(function(response) {
                            loginButton.style.display = 'none';
                            console.log(accessToken)
                        });
                    });
                });

            })();
        },
        seeArea() {
            let that = this
            let i = 0
            let j = 0

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
                                        "<div style='display:block;'>" + 'When you' + that.Area[i].action.description + ' then ' + that.Area[i].reaction.description +
                                        "</div></div></div>"
 //+ that.Area[i].action.description + ' then' + that.Area[i].reaction.description + '</MARQUEE></FONT>'
            }
        },
        createArea() {
            let that = this
            let i = 0
            let act = -1
            let react = -1
            for (i = 0; that.Action[i]; i++) {
                if (that.Action[i].name == that.ActionValue)
                act = i
            }
            for (i = 0; that.Reaction[i]; i++) {
                if (that.Reaction[i].name == that.ReactionValue)
                react = i
            }

            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

            var urlencoded = new URLSearchParams();
            urlencoded.append("actionId", that.Action[act].id);
            urlencoded.append("reactionId", that.Reaction[react].id);
            urlencoded.append("name", "I " + that.Action[act].name + "and " + that.Reaction[react].name);
            urlencoded.append("timeCheck", "-1");

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
                console.log(that.Area)
            })
            .catch(error => console.log('error', error));

        },
        SpotifyLogin(callback) {

            var CLIENT_ID = 'c299f837f4ff4872ab27a1a00a6c7bdf';
            var REDIRECT_URI = 'http://localhost8080/#/area/api/spotify';

            function getLoginURL(scopes) {
                return 'https://accounts.spotify.com/authorize?client_id=' + CLIENT_ID +
                '&redirect_uri=' + encodeURIComponent(REDIRECT_URI) +
                '&scope=' + encodeURIComponent(scopes.join(' ')) +
                '&response_type=token';
            }

            var url = getLoginURL([
                'user-read-email'
            ]);

            var width = 450,
            height = 730,
            left = (screen.width / 2) - (width / 2),
            top = (screen.height / 2) - (height / 2);
            var w = window.open(url, 'Spotify', 'menubar=no,location=no,resizable=no,scrollbars=no,status=no, width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
            console.log(url);


            window.addEventListener("message", function(event) {
                var hash = JSON.parse(event.data);
                if (hash.type == 'access_token') {
                    callback(hash.access_token);
                }
            }, false);
            if (!url)
            w.close();

        }
    },
    mounted(){
        let that = this

        console.log(that.userToken)
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
