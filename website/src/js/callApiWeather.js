const wAPI = require('../../server/api/weather.js')

const w = new WeatherAPI()

const data = w.weatherByCity("Paris")
console.log(data["name"])
function myFunction() {
    document.getElementById("demo").innerHTML = "il fait bô waische";
}
