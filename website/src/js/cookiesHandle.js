function refreshCookie() {
    var object = {
	name: "<%= userName %>",
	weather: {
	    cityNameT: "<%= weatherT.city.name %>",
	    cityName: "<%= weather.name %>",
	},
	yugioh: {
	    cardName: "<%= yugioh[0].name %>",
	},
	pokemon: {
	    pokemonName: "<%= pokemon.name %>",
	    itemName: "<%= pokemonItem.name %>",
	},
	checkbox: getChecked(),
    }
    num = 10;
    createCookie("<%= userName %>", JSON.stringify(object), num);
}

function checkUTF8(text) {
    var utf8Text = text;
    try {
	utf8Text = decodeURIComponent(escape(text));
    } catch(e) {
	console.log(e.message);
    }
    return utf8Text;
}
function createCookie(cname,cvalue,expires){
    var expirationdate = new Date();
    expirationdate.setTime(expirationdate.getTime() + (expires*24*60*60*1000));
    var expirationdatestring = "expires="+ expirationdate.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expirationdatestring + ";path=/";
}
function accessCookie(cname) {
    var cookiename = cname + "=";
    var decodedCookieString = checkUTF8(document.cookie);
    var cookieArray = decodedCookieString.split(';');
    for(var currentCookieIndex in cookieArray){
	var currentCookie = cookieArray[currentCookieIndex];
	while(currentCookie.charAt(0) == ' '){
	    currentCookie = currentCookie.substring(1);
	}
	if (currentCookie.indexOf(cookiename) === 0){
	    return currentCookie.substring(cookiename.length,currentCookie.length);
	}
    }
    return "";
}
function checkCookie() {
    var user = accessCookie("<%= userName %>");
    if (user!="") {
	console.log(user);
	var objectRecreated = JSON.parse(user);
	console.log(objectRecreated.checkbox);
	onlaunch(objectRecreated.checkbox);
    } else {
	var object = {
	    name: "<%= userName %>",
	    weather: {
		cityNameT: "<%= weatherT.city.name %>",
		cityName: "<%= weather.name %>",
	    },
	    yugioh: {
		cardName: "<%= yugioh[0].name %>",
	    },
	    pokemon: {
		pokemonName: "<%= pokemon.name %>",
		itemName: "<%= pokemonItem.name %>",
	    },
	    checkbox: getChecked(),
	}
	num = 10
	createCookie("<%= userName %>", JSON.stringify(object), num);
    }
    checkWidget();
}
function getChecked() {
    var anyBoxesChecked = [];
    var elements = document.getElementsByClassName("myCheck");
    for(var i=0; i<elements.length; i++) {
	anyBoxesChecked.push(elements[i].checked);
    }
    console.log(anyBoxesChecked);
    return anyBoxesChecked;
}
