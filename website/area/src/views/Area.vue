<template>
  <div>
   <h1>Test</h1>
   <button @click="handleClickSignIn" :disabled="!isLoaded">signIn</button>
  <ul id="menu-demo2">
	<li id="column"><a href="#">Lien menu 1</a>
		<ul>
			<li><a href="#">lien sous menu 1</a></li>
			<li><a href="#">lien sous menu 1</a></li>
			<li><a href="#">lien sous menu 1</a></li>
			<li><a href="#">lien sous menu 1</a></li>
		</ul>
	</li>
	<li id="column"><a href="#">Lien menu 2</a>
		<ul>
			<li><a href="#">Lien sous menu 2</a></li>
			<li><a href="#">Lien sous menu 2</a></li>
			<li><a href="#">Lien sous menu 2</a></li>
			<li><a href="#">Lien sous menu 2</a></li>
		</ul>
	</li>
	<li id="column"><a href="#">Lien menu 3</a>
		<ul>
			<li><a href="#">lien sous menu 3</a></li>
			<li><a href="#">lien sous menu 3</a></li>
			<li><a href="#">lien sous menu 3</a></li>
			<li><a href="#">lien sous menu 3</a></li>
		</ul>
	</li>
	<li><a href="#">Lien menu 4</a>
		<ul>
			<li><a href="#">Lien sous menu 4</a></li>
			<li><a href="#">Lien sous menu 4</a></li>
			<li><a href="#">Lien sous menu 4</a></li>
			<li><a href="#">Lien sous menu 4</a></li>
		</ul>
	</li>
</ul>
  </div>
 </template>

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
    handleClickSignIn(){
      this.$gAuth.signIn(function (user) {
         //on success do something
      console.log('user', user)
      }, function (error) {
         //on fail do something
      })
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
#body {
	background-color: rgb(95, 95, 95);
}
#menu-demo2{
padding:0;
margin:0;
list-style:none;
text-align:left;
}
#menu-demo2 li{
display: flex;
flex-direction: column;
position:relative;
width: 40%;
border-radius:8px 8px 0 0;
}
#menu-demo2 ul li{
display:inherit;
border-radius:0;
width: 100%;
}
#menu-demo2 ul li:hover{
border-radius:0;
}
#menu-demo2 ul li:last-child{
border-radius:0 0 8px 8px;
}
#menu-demo2 ul{
position: absolute;
z-index: 1000;
max-height:0;
left: 200px;
right: 0;
overflow:hidden;
-moz-transition: .8s all .3s;
-webkit-transition: .8s all .3s;
transition: .8s all .3s;
}
#menu-demo2 li:hover ul{
max-height:15em;
}

/* background des liens menus */
#menu-demo2 li{
background-color: rgb(95, 95, 95);
}
/* background des liens sous menus */
#menu-demo2 li{
background:rgb(95, 95, 95);
}
/* background des liens menus et sous menus au survol */
#menu-demo2 li{
background:rgb(95, 95, 95);
}
/* les a href */
#menu-demo2 a{
text-decoration:none;
display:block;
padding:8px 32px;
color:#fff;
font-family:arial;
}
#menu-demo2 ul a{
padding:8px 0;
}
#menu-demo2 li:hover li a{
color:#fff;
text-transform:inherit;
}
#menu-demo2 li:hover a, #menu-demo2 li li:hover a{
color:#000;
}
</style>
