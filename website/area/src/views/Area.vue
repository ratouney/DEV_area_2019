<template>
  <div>
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <title>jQuery UI Menu - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">

   <h1>Test</h1>
   <button @click="GoogleLogin" :disabled="!isLoaded">signIn</button>
   <button @click="SpotifyLogin" :disabled="!isLoaded">SignIn With Spotify</button>
  <button class="accordion">Google</button>
<div class="panel">
  
</div>

<button class="accordion">Spotify</button>
<div class="panel">
  <p>Lorem ipsum...</p>
</div>

<button class="accordion">Section 3</button>
<div class="panel">
  <p>Lorem ipsum...</p>
</div>
  </div>
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
             isLoaded: false
         }
     },
     computed: {
     },
     methods: {
         GoogleLogin(){
             this.$gAuth.signIn(function (user) {
                 //on success do something
                 console.log('user', user)
             }, function (error) {
                 //on fail do something
             })
         },
         SpotifyLogin(callback) {

             var CLIENT_ID = '3f6be5c8306741c8ab06713da0a92f59';
             var REDIRECT_URI = 'http://localhost:8080/#/account';

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
     }
 }

</script>

<style scoped>
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc;
}

.accordion:after {
  content: '\002B';
  color: #777;
  font-weight: bold;
  float: right;
  margin-left: 5px;
}

.active:after {
  content: "\2212";
}

.panel {
  padding: 0 18px;
  background-color: white;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
}
</style>
