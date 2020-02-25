<template>
    <html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="../style/homepage.css">
    </head>
    <body>
        <div class="header" style="background-color: #333; color: white;">
            <h2 style="color: white; font-size: 300%;">Your Personal AREA</h2>
            <p style="color: white; font-size: 80%;">Create your own Action and Reaction : <br>
                Yes, you can ask our Area to send you a mail to prevent you <br>when you recive a mail
            </p>
        </div>
        <div id="navbar" class="navbar">
            <div style="max-width: 90%; margin: auto;">
                <a href="dashboard"></a>
                <div class="topnav-right">
                    <router-link :to="{ name: 'area', params: {id: 1234}, query: { debug: this.userToken } }" v-if="userToken !== ''">Manage Area</router-link>
                    <a v-if="userToken !== ''" href="#" @click="disconnect()">Disconnect</a>
                    <a v-if="userToken == ''" href="#" onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Login</a>
                    <a v-if="userToken == ''" class="active" href="#" onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Register</a>
                </div>
            </div>
        </div>
        <div style="max-width: 90%; margin: auto;">
            <!--============================================ Modal container LOGIN ============================================-->
            <div id="id01" class="modal">
                <form class="modal-content animate" >
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        <h1>Login</h1>
                        <label for="username"><b>Username</b></label>
                        <input v-model="username" type="text" placeholder="Enter Username" name="username" required>
                        <label for="password"><b>Password</b></label>
                        <input v-model="pswd" type="password" placeholder="Enter Password" name="password" required>
                        <p>Le message est : {{ userToken }}</p>
                        <button @click="login()">Login</button>
                    </div>
                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                    </div>
                </form>
            </div>
            <!--==============================================================================================================-->

            <!--========================================== Modal container REGISTER ==========================================-->
            <div id="id02" class="modal">
                <form class="modal-content animate">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        <h1>Register</h1>
                        <p>Please fill in this form to create an account.</p>
                        <hr>
                        <label for="username"><b>Username</b></label>
                        <input v-model="username" type="text" placeholder="Enter Username" name="username" required>
                        <label for="password"><b>Password</b></label>
                        <input v-model="pswd" type="password" placeholder="Enter Password" name="password" required>
                        <hr>
                        <button type="submit" class="registerbtn">Register</button>
                    </div>
                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
                    </div>
                    <!--    <div class="container signin">
                    <p>Already have an account? <a href="login">Sign in</a>.</p>
                </div>-->
            </form>
        </div>

        <!--==============================================================================================================-->
    </div>
    <div>
        <button href="#" onclick="document.getElementById('id02').style.display='block'" style="width:100%; background-color: #4CAF50; font-size: 30px;">Ready ? Try it NOW</button>
    </div>
    <div class="container" style="background-color: #f1f0f0;" height="350px">

    </div>
</body>
</html>
</template>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script> @import"../js/accordion.js";</script>
<script> @import"../js/scrollable.js";</script>
<script> @import"../js/popup.js";</script>
<script> @import"../js/draggable.js";</script>
<style> @import"../style/homepage.css";</style>
<style> @import"../style/homepage.css";</style>
<script>
// @ is an alias to /src
import Child from '@/views/Area.vue'
export default {
  name: 'home',
  components: {
      Child
  },
  data () {
    return {
        username: '',
        pswd: '',
        user: '',
        userToken: '',
    }
  },
  methods: {
      login() {
          let that = this
          var myHeaders = new Headers();
          myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

          var urlencoded = new URLSearchParams();
          urlencoded.append("username", this.username);
          urlencoded.append("password", this.pswd);

          var requestOptions = {
              method: 'POST',
              headers: myHeaders,
              body: urlencoded,
              redirect: 'follow'
          };

          fetch("/session/login", requestOptions)
          .then(response => response.text())
          .then(function(result) {
              that.userToken = JSON.parse(result).data
              document.getElementById('id01').style.display='none'
          })
          .catch(error => console.log('error', error));
      },
      disconnect() {
          this.userToken = '';
      },
      register() {
          var myHeaders = new Headers();
          myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

          var urlencoded = new URLSearchParams();
          urlencoded.append("username", this.username);
          urlencoded.append("password", this.pswd);
          urlencoded.append("email", "ratouney1998@gmail.com");

          var requestOptions = {
              method: 'POST',
              headers: myHeaders,
              body: urlencoded,
              redirect: 'follow'
          };

          fetch("/user/new", requestOptions)
          .then(response => response.text())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));
      }
}

}
</script>
