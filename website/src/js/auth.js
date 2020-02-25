function initClient() {
var GoogleAuth; // Google Auth object.
    console.log("ni queue ta mère");
  gapi.client.init({
      'apiKey': 'AIzaSyC4OBf3bDwMXZ09TQSyunxswsHHi8ydp8M',
      'clientId': '602804385318-hj4udn9f6rg3u3gb5ds45tiv3amdc5vr.apps.googleusercontent.com',
      'scope': 'https://www.googleapis.com/auth/drive.metadata.readonly',
      'discoveryDocs': ['https://www.googleapis.com/discovery/v1/apis/drive/v3/rest']
  }).then(function () {
      GoogleAuth = gapi.auth2.getAuthInstance();

      // Listen for sign-in state changes.
      GoogleAuth.isSignedIn.listen(updateSigninStatus);
  });
}
