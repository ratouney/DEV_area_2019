import sheet from '../../../../app/src/main/res/drawable/sheet.png';
import pokemon from '../../../../app/src/main/res/drawable/pokemon.png';
import { Action } from '../../../backend/src/entities';
<template>
    <html>
    <div id="area">
        <div id="navbar" class="navbar">
            <div style="max-width: 90%; margin: auto;">
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

            <h1>Test</h1>
            <button @click="test()" id="btn-login">test</button>
            <button @click="SpotifyLogin">SignIn With Spotify</button>


            <p>Select your action and your reaction</p>

            <label for="Action">Select your action:</label>

            <select @change="onChangeAction($event)" class="form-control"  id="action">
                <option value="newMail">New e-mail</option>
                <option value="changeSheet">Change Sheet</option>
                <option value="limitUV">UV limit</option>
                <option value="dailyPic">Daily Photo</option>
                <option value="dailyWeather">Daily Weather</option>
                <option value="newVote">New vote</option>
                <option value="newCom">New Comment</option>
                <option value="newFav">New Favorite</option>
                <option value="newPicTag">New pic in the tag</option>
            </select> 
            <div v-if="this.ActionValue === 'newMail'" style="display:block;">
                <textarea id="w3mission" rows="4" cols="50">
                    Your email here
                </textarea>
            </div>
            <br/><br/>
            <label for="Reaction">Select your reaction:</label>

            <select id="reaction">
                <option value="sendMail">Send an e-mail</option>
                <option value="createSheet">Create a Sheet</option>
                <option value="createDraft">create a draft</option>
                <option value="changeSound">Daily Photo</option>
                <option value="pauseMusic">Music on pause</option>
                <option value="uploadPic">Upload a pic</option>
                <option value="changeBio">Change Bio</option>
            </select> 
            <!-- créer un script pour récupérer la value et avec un v-if afficher le form correspondant -->
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
            ActionValue: 'newMail',
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
        GoogleLogin(){
            let self = this
            this.$gAuth.signIn(function (user) {
                console.log(self.string)
                self.googleAccessToken = user.uc.access_token
                console.log(self.googleAccessToken)
//                console.log('access token : ', user.uc.access_token)
//                console.log(user)
            }, function (error) {
            })
        },
        accordion() {
            //axios.get('http://51.75.69.196:3001/session/login')
            var acc = document.getElementsByClassName("accordion");
            var i;
            for (i = 0; i < acc.length; i++) {
                acc[i].addEventListener("click", function() {
                    this.classList.toggle("active");
                    var panel = this.nextElementSibling;
                    if (panel.style.maxHeight) {
                        panel.style.maxHeight = null;
                    } else {
                        panel.style.maxHeight = panel.scrollHeight + "px";
                    }
                });
            }
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
                    console.log('test2')
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
        let checkGauthLoad = setInterval(function(){
            that.isInit = that.$gAuth.isInit
            console.log('checked', that.isInit)
            if(that.isInit) clearInterval(checkGauthLoad)
        }, 1000);
    },
}
</script>