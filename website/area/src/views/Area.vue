<template>
  <div>
   <h1>Test</h1>
   <button @click="handleClickSignIn" :disabled="!isLoaded">signIn</button>
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
