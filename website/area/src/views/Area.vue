import sheet from '../../../../app/src/main/res/drawable/sheet.png';
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
            <button @click="SpotifyLogin" :disabled="!isLoaded">SignIn With Spotify</button>



            <!--========================================== Modal container Reactions ==========================================-->

            <div id="id03" class="modal">
                <form class="modal-content animate" action="/register" method="post">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        <h1>Reception d'un mail</h1>
                        <p>Envoyer un message lors de la reception d'un e-mail</p>
                        <!-- menu déroulant avec pour l'instant : Envoie d'un mail, Création d'une spreadsheet-->
                        <button type="submit" class="registerbtn">Register</button>
                    </div>
                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
                    </div>
                </form>
            </div>

            <!--==============================================================================================================-->

            <button @click="accordion" class="accordion">GMail</button>
            <div class="panel">
                <div class="widget" name="gmail">
                    <button v-if="googleAccessToken == ''" @click="GoogleLogin" :disabled="!isLoaded">signIn</button>
                    <button v-if="googleAccessToken !== ''" href="#" onclick="document.getElementById('id03').style.display='block'" style="width:100%; background-color: #00000; font-size: 30px;">Action 1</button>
                    <div v-if="googleAccessToken !== ''" class="portlet">
                        <div class="portlet-header">Gmail</div>
                        <div class="portlet-content">
                            <div class="container">
                                <h1>Reception d'un mail</h1>
                                <p>Envoyer un message lors de la reception d'un e-mail</p>
                                <!-- menu déroulant avec pour l'instant : Envoie d'un mail, Création d'une spreadsheet-->
                                <div style="display:block;">
                                    <textarea id="w3mission" rows="4" cols="50">
                                        Your email here
                                    </textarea>
                                </div>
                                <button type="submit" class="registerbtn">Register</button>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <button @click="accordion" class="accordion">Spotify</button>
            <div class="panel">
                <button href="#" onclick="document.getElementById('id03').style.display='block'" style="width:100%; background-color: #00000; font-size: 30px;">Action 2</button>
            </div>

            <button @click="accordion" class="accordion">Sheet</button>
            <div class="panel">
                <p>Lorem ipsum...</p>
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
            componentKey: 0,
            googleAccessToken: '',
            isLoaded: false
        }
    },
    computed: {
    },
    methods: {
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
            that.isLoaded = that.$gAuth.isLoaded()
            console.log('checked', that.isLoaded)
            if(that.isLoaded) clearInterval(checkGauthLoad)
        }, 1000);
    },
}

</script>
